/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.java.declarations

import org.jetbrains.kotlin.KtSourceElement
import org.jetbrains.kotlin.fir.FirImplementationDetail
import org.jetbrains.kotlin.fir.FirModuleData
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl
import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl
import org.jetbrains.kotlin.fir.expressions.FirAnnotation
import org.jetbrains.kotlin.fir.expressions.FirExpression
import org.jetbrains.kotlin.fir.java.enhancement.FirEmptyJavaAnnotationList
import org.jetbrains.kotlin.fir.java.enhancement.FirJavaAnnotationList
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.fir.visitors.FirTransformer
import org.jetbrains.kotlin.fir.visitors.FirVisitor
import org.jetbrains.kotlin.fir.visitors.transformSingle
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.properties.Delegates

@OptIn(FirImplementationDetail::class)
class FirJavaValueParameter @FirImplementationDetail constructor(
    override val source: KtSourceElement?,
    override val moduleData: FirModuleData,
    override val origin: FirDeclarationOrigin.Java,
    override val attributes: FirDeclarationAttributes,
    override var returnTypeRef: FirTypeRef,
    override val name: Name,
    override val symbol: FirValueParameterSymbol,
    val annotationList: FirJavaAnnotationList,
    var lazyDefaultValue: Lazy<FirExpression>?,
    override val containingDeclarationSymbol: FirFunctionSymbol<*>,
    override val isVararg: Boolean,
) : FirValueParameter() {
    override var defaultValue: FirExpression?
        get() = lazyDefaultValue?.value
        set(value) {
            lazyDefaultValue = value?.let(::lazyOf)
        }

    init {
        symbol.bind(this)

        @OptIn(ResolveStateAccess::class)
        this.resolveState = FirResolvePhase.ANALYZED_DEPENDENCIES.asResolveState()
    }

    override val isCrossinline: Boolean
        get() = false

    override val isNoinline: Boolean
        get() = false

    override val valueParameterKind: FirValueParameterKind
        get() = FirValueParameterKind.Regular

    override val isVal: Boolean
        get() = true

    override val isVar: Boolean
        get() = false

    override val annotations: List<FirAnnotation> get() = annotationList

    override val receiverParameter: FirReceiverParameter?
        get() = null

    override val deprecationsProvider: DeprecationsProvider
        get() = EmptyDeprecationsProvider

    override val initializer: FirExpression?
        get() = null

    override val delegate: FirExpression?
        get() = null

    override val getter: FirPropertyAccessor?
        get() = null

    override val setter: FirPropertyAccessor?
        get() = null

    override val backingField: FirBackingField?
        get() = null

    override val controlFlowGraphReference: FirControlFlowGraphReference?
        get() = null

    override val typeParameters: List<FirTypeParameterRef>
        get() = emptyList()

    override val status: FirDeclarationStatus
        get() = FirResolvedDeclarationStatusImpl.DEFAULT_STATUS_FOR_STATUSLESS_DECLARATIONS

    override val containerSource: DeserializedContainerSource?
        get() = null

    override val dispatchReceiverType: ConeSimpleKotlinType?
        get() = null

    override val contextParameters: List<FirValueParameter>
        get() = emptyList()

    override fun <R, D> acceptChildren(visitor: FirVisitor<R, D>, data: D) {
        returnTypeRef.accept(visitor, data)
        annotations.forEach { it.accept(visitor, data) }
        defaultValue?.accept(visitor, data)
    }

    override fun <D> transformChildren(transformer: FirTransformer<D>, data: D): FirValueParameter {
        transformReturnTypeRef(transformer, data)
        transformOtherChildren(transformer, data)
        return this
    }

    override fun <D> transformReturnTypeRef(transformer: FirTransformer<D>, data: D): FirValueParameter {
        returnTypeRef = returnTypeRef.transformSingle(transformer, data)
        return this
    }

    override fun <D> transformReceiverParameter(transformer: FirTransformer<D>, data: D): FirValueParameter {
        return this
    }

    override fun <D> transformContextParameters(transformer: FirTransformer<D>, data: D): FirValueParameter {
        return this
    }

    override fun <D> transformInitializer(transformer: FirTransformer<D>, data: D): FirValueParameter {
        return this
    }

    override fun <D> transformDelegate(transformer: FirTransformer<D>, data: D): FirValueParameter {
        return this
    }

    override fun <D> transformGetter(transformer: FirTransformer<D>, data: D): FirValueParameter {
        return this
    }

    override fun <D> transformSetter(transformer: FirTransformer<D>, data: D): FirValueParameter {
        return this
    }

    override fun <D> transformBackingField(transformer: FirTransformer<D>, data: D): FirValueParameter {
        return this
    }

    override fun replaceAnnotations(newAnnotations: List<FirAnnotation>) {
        shouldNotBeCalled(::replaceAnnotations, ::annotations)
    }

    override fun <D> transformAnnotations(transformer: FirTransformer<D>, data: D): FirValueParameter {
        return this
    }

    override fun <D> transformOtherChildren(transformer: FirTransformer<D>, data: D): FirValueParameter {
        defaultValue = defaultValue?.transformSingle(transformer, data)
        return this
    }

    override fun <D> transformTypeParameters(transformer: FirTransformer<D>, data: D): FirValueParameter {
        return this
    }

    override fun <D> transformStatus(transformer: FirTransformer<D>, data: D): FirValueParameter {
        return this
    }

    override fun replaceReturnTypeRef(newReturnTypeRef: FirTypeRef) {
        returnTypeRef = newReturnTypeRef
    }

    override fun replaceReceiverParameter(newReceiverParameter: FirReceiverParameter?) {}

    override fun replaceDeprecationsProvider(newDeprecationsProvider: DeprecationsProvider) {

    }

    override fun replaceInitializer(newInitializer: FirExpression?) {
    }

    override fun replaceDelegate(newDelegate: FirExpression?) {}

    override fun replaceControlFlowGraphReference(newControlFlowGraphReference: FirControlFlowGraphReference?) {
    }

    override fun replaceDefaultValue(newDefaultValue: FirExpression?) {
        error("Java value parameter cannot has default value")
    }

    override fun replaceGetter(newGetter: FirPropertyAccessor?) {
    }

    override fun replaceSetter(newSetter: FirPropertyAccessor?) {
    }

    override fun replaceContextParameters(newContextParameters: List<FirValueParameter>) {
        error("Body cannot be replaced for FirJavaValueParameter")
    }

    override fun replaceStatus(newStatus: FirDeclarationStatus) {
        error("Status cannot be replaced for FirJavaValueParameter")
    }
}

@FirBuilderDsl
class FirJavaValueParameterBuilder {
    var source: KtSourceElement? = null
    lateinit var moduleData: FirModuleData
    var attributes: FirDeclarationAttributes = FirDeclarationAttributes()
    lateinit var returnTypeRef: FirTypeRef
    lateinit var name: Name
    var annotationList: FirJavaAnnotationList = FirEmptyJavaAnnotationList
    var defaultValue: Lazy<FirExpression>? = null
    lateinit var containingDeclarationSymbol: FirFunctionSymbol<*>
    var isVararg: Boolean by Delegates.notNull()
    var isFromSource: Boolean by Delegates.notNull()

    @OptIn(FirImplementationDetail::class)
    fun build(): FirJavaValueParameter {
        return FirJavaValueParameter(
            source,
            moduleData,
            origin = javaOrigin(isFromSource),
            attributes,
            returnTypeRef,
            name,
            symbol = FirValueParameterSymbol(),
            annotationList,
            defaultValue,
            containingDeclarationSymbol,
            isVararg,
        )
    }
}

inline fun buildJavaValueParameter(init: FirJavaValueParameterBuilder.() -> Unit): FirJavaValueParameter {
    return FirJavaValueParameterBuilder().apply(init).build()
}

@OptIn(ExperimentalContracts::class)
inline fun buildJavaValueParameterCopy(original: FirJavaValueParameter, init: FirJavaValueParameterBuilder.() -> Unit): FirValueParameter {
    contract {
        callsInPlace(init, InvocationKind.EXACTLY_ONCE)
    }
    val copyBuilder = FirJavaValueParameterBuilder()
    copyBuilder.source = original.source
    copyBuilder.moduleData = original.moduleData
    copyBuilder.attributes = original.attributes.copy()
    copyBuilder.isFromSource = original.origin.fromSource
    copyBuilder.returnTypeRef = original.returnTypeRef
    copyBuilder.name = original.name
    copyBuilder.annotationList = original.annotationList
    copyBuilder.defaultValue = original.lazyDefaultValue
    copyBuilder.containingDeclarationSymbol = original.containingDeclarationSymbol
    copyBuilder.isVararg = original.isVararg
    return copyBuilder.apply(init).build()
}
