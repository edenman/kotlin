/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.common.lower

import org.jetbrains.kotlin.backend.common.*
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.builders.irGet
import org.jetbrains.kotlin.ir.builders.irSetField
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.expressions.impl.*
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.IrValueParameterSymbol
import org.jetbrains.kotlin.ir.symbols.IrValueSymbol
import org.jetbrains.kotlin.ir.types.classOrNull
import org.jetbrains.kotlin.ir.util.copyTypeAndValueArgumentsFrom
import org.jetbrains.kotlin.ir.util.dump
import org.jetbrains.kotlin.ir.util.parentAsClass
import org.jetbrains.kotlin.ir.util.patchDeclarationParents
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.ir.visitors.transformChildrenVoid
import org.jetbrains.kotlin.utils.addToStdlib.assignFrom

interface InnerClassesSupport {
    fun getOuterThisField(innerClass: IrClass): IrField
    fun getInnerClassConstructorWithOuterThisParameter(innerClassConstructor: IrConstructor): IrConstructor
    fun getInnerClassOriginalPrimaryConstructorOrNull(innerClass: IrClass): IrConstructor?
}

/**
 * Adds 'outer this' fields to inner classes.
 */
open class InnerClassesLowering(val context: CommonBackendContext) : DeclarationTransformer {
    private val innerClassesSupport = context.innerClassesSupport

    override val withLocalDeclarations: Boolean get() = true

    override fun transformFlat(declaration: IrDeclaration): List<IrDeclaration>? {
        if (declaration is IrClass && declaration.isInner) {
            declaration.declarations += innerClassesSupport.getOuterThisField(declaration)
        } else if (declaration is IrConstructor) {
            val irClass = declaration.parentAsClass
            if (!irClass.isInner) return null

            val newConstructor = lowerConstructor(declaration)
            val oldConstructorParameterToNew = innerClassesSupport.primaryConstructorParameterMap(declaration)
            val variableRemapper = VariableRemapper(oldConstructorParameterToNew)
            for ((oldParam, newParam) in oldConstructorParameterToNew.entries) {
                newParam.defaultValue = oldParam.defaultValue?.let { oldDefault ->
                    context.irFactory.createExpressionBody(
                        startOffset = oldDefault.startOffset,
                        endOffset = oldDefault.endOffset,
                        expression = oldDefault.expression.transform(variableRemapper, null).patchDeclarationParents(newConstructor),
                    )
                }
            }

            return listOf(newConstructor)
        }

        return null
    }

    private fun lowerConstructor(irConstructor: IrConstructor): IrConstructor {
        val loweredConstructor = innerClassesSupport.getInnerClassConstructorWithOuterThisParameter(irConstructor)
        val outerThisParameter = loweredConstructor.parameters[0]

        val irClass = irConstructor.parentAsClass
        val parentThisField = innerClassesSupport.getOuterThisField(irClass)

        irConstructor.body?.let { blockBody ->
            if (blockBody !is IrBlockBody) throw AssertionError("Unexpected constructor body: ${irConstructor.body}")

            loweredConstructor.body = context.irFactory.createBlockBody(blockBody.startOffset, blockBody.endOffset) {

                if (irConstructor.shouldInitializeOuterThis()) {
                    context.createIrBuilder(irConstructor.symbol, irConstructor.startOffset, irConstructor.endOffset).apply {
                        statements.add(0, irSetField(irGet(irClass.thisReceiver!!), parentThisField, irGet(outerThisParameter)))
                    }
                }

                statements.addAll(blockBody.statements)

                if (statements.find { it is IrInstanceInitializerCall } == null) {
                    val delegatingConstructorCall =
                        statements.find { it is IrDelegatingConstructorCall } as IrDelegatingConstructorCall?
                            ?: throw AssertionError("Delegating constructor call expected: ${irConstructor.dump()}")
                    delegatingConstructorCall.apply { dispatchReceiver = IrGetValueImpl(startOffset, endOffset, outerThisParameter.symbol) }
                }
                patchDeclarationParents(loweredConstructor)

                val oldConstructorParameterToNew = innerClassesSupport.primaryConstructorParameterMap(irConstructor)
                transformChildrenVoid(VariableRemapper(oldConstructorParameterToNew))
            }
        }

        return loweredConstructor
    }

    private fun IrConstructor.shouldInitializeOuterThis(): Boolean {
        val irBlockBody = body as? IrBlockBody ?: return false
        // Constructors are either delegating to a constructor of super class (and initializing an instance of this class),
        // or delegating to a constructor of this class.
        // Don't initialize outer 'this' in constructor delegating to this,
        // otherwise final 'this$0' field will be initialized twice (in delegating constructor and in original constructor),
        // which is legal, but causes a performance regression on JVM (KT-50039).
        return irBlockBody.statements.any { it is IrInstanceInitializerCall }
    }
}

private fun InnerClassesSupport.primaryConstructorParameterMap(originalConstructor: IrConstructor): Map<IrValueParameter, IrValueParameter> {
    val oldConstructorParameterToNew = HashMap<IrValueParameter, IrValueParameter>()

    val loweredConstructor = getInnerClassConstructorWithOuterThisParameter(originalConstructor)

    originalConstructor.parameters.forEachIndexed { index, parameter ->
        oldConstructorParameterToNew[parameter] = loweredConstructor.parameters[index]
    }

    assert(loweredConstructor.parameters.size == originalConstructor.parameters.size)

    return oldConstructorParameterToNew
}

/**
 * Replaces `this` with 'outer this' field references.
 */
open class InnerClassesMemberBodyLowering(val context: CommonBackendContext) : BodyLoweringPass {
    private val innerClassesSupport = context.innerClassesSupport

    override fun lower(irFile: IrFile) {
        runOnFilePostfix(irFile, true)
    }

    private val IrValueSymbol.classForImplicitThis: IrClass?
        // TODO: is this the correct way to get the class?
        get() =
            if (this is IrValueParameterSymbol && owner.kind == IrParameterKind.DispatchReceiver) {
                owner.type.classOrNull?.owner
            } else
                null

    override fun lower(irBody: IrBody, container: IrDeclaration) {
        val irClass = container.parent as? IrClass ?: return

        if (!irClass.isInner) return

        if (container is IrField || container is IrAnonymousInitializer || container is IrValueParameter) {
            val primaryConstructor = innerClassesSupport.getInnerClassOriginalPrimaryConstructorOrNull(irClass)
            if (primaryConstructor != null) {
                val oldConstructorParameterToNew = innerClassesSupport.primaryConstructorParameterMap(primaryConstructor)
                irBody.transformChildrenVoid(VariableRemapper(oldConstructorParameterToNew))
            }
        }

        if (container is IrFunction) {
            container.parameters.forEach { parameter ->
                parameter.defaultValue?.fixThisReference(irClass, container)
            }
        }

        irBody.fixThisReference(irClass, container)
    }

    private fun IrBody.fixThisReference(irClass: IrClass, container: IrDeclaration) {
        val enclosingFunction: IrDeclaration? = run {
            var current: IrDeclaration? = container
            while (current != null && current !is IrFunction && current !is IrClass) {
                current = current.parent as? IrDeclaration
            }
            current
        }
        transformChildrenVoid(object : IrElementTransformerVoidWithContext() {
            override fun visitClassNew(declaration: IrClass): IrStatement =
                declaration

            override fun visitGetValue(expression: IrGetValue): IrExpression {
                expression.transformChildrenVoid(this)

                val implicitThisClass = expression.symbol.classForImplicitThis
                if (implicitThisClass == null || implicitThisClass == irClass) return expression

                val startOffset = expression.startOffset
                val endOffset = expression.endOffset
                val origin = expression.origin
                val function = (currentFunction?.irElement ?: enclosingFunction) as? IrFunction
                val enclosingThisReceiver = function?.dispatchReceiverParameter ?: irClass.thisReceiver!!

                var irThis: IrExpression = IrGetValueImpl(startOffset, endOffset, enclosingThisReceiver.symbol, origin)
                var innerClass = irClass
                while (innerClass != implicitThisClass) {
                    if (!innerClass.isInner) {
                        // Captured 'this' unrelated to inner classes nesting hierarchy, leave it as is -
                        // should be transformed by closures conversion.
                        return expression
                    }

                    irThis = if (function is IrConstructor && irClass == innerClass) {
                        // Might be before a super() call (e.g. an argument to one), in which case the JVM bytecode verifier will reject
                        // an attempt to access the field. Good thing we have a local variable as well.
                        IrGetValueImpl(startOffset, endOffset, function.parameters[0].symbol, origin)
                    } else {
                        val outerThisField = innerClassesSupport.getOuterThisField(innerClass)
                        IrGetFieldImpl(startOffset, endOffset, outerThisField.symbol, outerThisField.type, irThis, origin)
                    }
                    innerClass = innerClass.parentAsClass
                }
                return irThis
            }
        })
    }
}

open class InnerClassConstructorCallsLowering(val context: CommonBackendContext) : BodyLoweringPass {
    private val innerClassesSupport: InnerClassesSupport = context.innerClassesSupport

    override fun lower(irBody: IrBody, container: IrDeclaration) {
        irBody.transformChildrenVoid(object : IrElementTransformerVoid() {
            override fun visitConstructorCall(expression: IrConstructorCall): IrExpression {
                expression.transformChildrenVoid(this)

                if (expression.dispatchReceiver == null) return expression
                val callee = expression.symbol
                val parent = callee.owner.parentAsClass
                if (!parent.isInner) return expression

                val newCallee = innerClassesSupport.getInnerClassConstructorWithOuterThisParameter(callee.owner)
                val classTypeParametersCount = expression.typeArguments.size - expression.constructorTypeArgumentsCount
                val newCall = IrConstructorCallImpl.fromSymbolOwner(
                    expression.startOffset, expression.endOffset, expression.type, newCallee.symbol, classTypeParametersCount, expression.origin
                )

                newCall.copyTypeArgumentsFrom(expression)
                newCall.arguments.assignFrom(expression.arguments)

                return newCall
            }

            override fun visitDelegatingConstructorCall(expression: IrDelegatingConstructorCall): IrExpression {
                expression.transformChildrenVoid(this)

                if (expression.dispatchReceiver == null) return expression
                val classConstructor = expression.symbol.owner
                if (!classConstructor.parentAsClass.isInner) return expression

                val newCallee = innerClassesSupport.getInnerClassConstructorWithOuterThisParameter(classConstructor)
                val newCall = IrDelegatingConstructorCallImpl(
                    expression.startOffset, expression.endOffset, context.irBuiltIns.unitType, newCallee.symbol,
                    typeArgumentsCount = expression.typeArguments.size,
                ).apply { copyTypeArgumentsFrom(expression) }

                newCall.arguments.assignFrom(expression.arguments)

                return newCall
            }


            override fun visitRawFunctionReference(expression: IrRawFunctionReference): IrExpression {
                expression.transformChildrenVoid(this)

                val callee = expression.symbol as? IrConstructorSymbol ?: return expression
                val parent = callee.owner.parent as? IrClass ?: return expression
                if (!parent.isInner) return expression

                val newCallee = innerClassesSupport.getInnerClassConstructorWithOuterThisParameter(callee.owner)

                expression.symbol = newCallee.symbol
                return expression
            }

            override fun visitFunctionReference(expression: IrFunctionReference): IrExpression {
                expression.transformChildrenVoid(this)

                val callee = expression.symbol as? IrConstructorSymbol ?: return expression
                val parent = callee.owner.parent as? IrClass ?: return expression
                if (!parent.isInner) return expression

                val newCallee = innerClassesSupport.getInnerClassConstructorWithOuterThisParameter(callee.owner)
                val newReflectionTarget = expression.reflectionTarget?.let { reflectionTarget ->
                    when (reflectionTarget) {
                        is IrConstructorSymbol -> innerClassesSupport.getInnerClassConstructorWithOuterThisParameter(reflectionTarget.owner)
                        is IrSimpleFunctionSymbol -> null
                    }
                }

                val newReference = expression.run {
                    IrFunctionReferenceImpl(
                        startOffset,
                        endOffset,
                        type,
                        newCallee.symbol,
                        typeArgumentsCount = typeArguments.size,
                        reflectionTarget = newReflectionTarget?.symbol,
                        origin = origin
                    )
                }
                newReference.copyTypeAndValueArgumentsFrom(expression)
                return newReference
            }
            // TODO callable references?
        })
    }
}
