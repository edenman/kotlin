// RUN_PIPELINE_TILL: FRONTEND
// LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect +AllowContractsForNonOverridableMembers +AllowReifiedGenericsInContracts
// LANGUAGE: -AllowContractsOnPropertyAccessors
// OPT_IN: kotlin.contracts.ExperimentalContracts
// DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER -NOTHING_TO_INLINE -ABSTRACT_FUNCTION_IN_NON_ABSTRACT_CLASS -ABSTRACT_FUNCTION_WITH_BODY -UNUSED_PARAMETER -UNUSED_VARIABLE -EXPERIMENTAL_FEATURE_WARNING

import kotlin.contracts.*


// ============= Class =====================
open class Class {
    constructor(f: () -> Unit = {}) {
        contract { callsInPlace(f, InvocationKind.EXACTLY_ONCE) }
        f()
    }

    fun member(x: Boolean) {
        contract { returns() implies (x) }
    }

    inline fun inlineMember(x: Boolean) {
        contract { returns() implies (x) }
    }

    abstract fun abstractMember(x: Boolean) {
        <!CONTRACT_NOT_ALLOWED!>contract<!> { returns() implies (x) }
    }

    open fun openMemeber(x: Boolean) {
        <!CONTRACT_NOT_ALLOWED!>contract<!> { returns() implies (x) }
    }

    suspend fun suspendMember(x: Boolean) {
        contract { returns() implies (x) }
    }
}

open class Inheritor : Class() {
    override fun openMemeber(x: Boolean) {
        <!CONTRACT_NOT_ALLOWED!>contract<!> { returns() implies (x) }
    }

    final override fun abstractMember(x: Boolean) {
        <!CONTRACT_NOT_ALLOWED!>contract<!> { returns() implies (x) }
    }
}

interface Interface {
    fun implicitlyOpenMember(x: Boolean) {
        <!CONTRACT_NOT_ALLOWED!>contract<!> { returns() implies (x) }
    }
}

// ============= Top-level =====================
fun topLevel(x: Boolean) {
    contract { returns() implies (x) }
}

inline fun inlineTopLevel(x: Boolean) {
    contract { returns() implies (x) }
}

suspend fun suspendTopLevel(x: Boolean) {
    contract { returns() implies (x) }
}

// Top-level operator
operator fun Boolean.plus(x: Boolean): Boolean {
    <!CONTRACT_NOT_ALLOWED!>contract<!> { returns() implies (x) }
    return x
}

val topLevelLambda: (Boolean) -> Unit = { x: Boolean ->
    <!CONTRACT_NOT_ALLOWED!>contract<!> { returns() implies (x) }
}

val topLevelAnonymousFunction = fun (x: Boolean) {
    <!CONTRACT_NOT_ALLOWED!>contract<!> { returns() implies (x) }
}

var topLevelPropertyAccessors: Int? = 42
    get() {
        <!CONTRACT_NOT_ALLOWED, CONTRACT_NOT_ALLOWED!>contract<!> { returns() implies (field != null) }
        return 42
    }
    set(value) {
        <!CONTRACT_NOT_ALLOWED, CONTRACT_NOT_ALLOWED!>contract<!> { returns() implies (field != null) }
    }


// ============= Local =====================
fun test() {
    fun localDeclaration(x: Boolean) {
        <!CONTRACT_NOT_ALLOWED!>contract<!> { returns() implies (x) }
    }

    suspend fun suspendlocalDeclaration(x: Boolean) {
        <!CONTRACT_NOT_ALLOWED!>contract<!> { returns() implies (x) }
    }

    val localAnonymousFunction = fun (x: Boolean) {
        <!CONTRACT_NOT_ALLOWED!>contract<!> { returns() implies (x) }
    }

    val localLambda: (Boolean) -> Unit = { x: Boolean ->
        <!CONTRACT_NOT_ALLOWED!>contract<!> { returns() implies (x) }
    }
}

/* GENERATED_FIR_TAGS: anonymousFunction, classDeclaration, contractCallsEffect, contractConditionalEffect, contracts,
equalityExpression, funWithExtensionReceiver, functionDeclaration, functionalType, getter, inline, integerLiteral,
interfaceDeclaration, lambdaLiteral, localFunction, localProperty, nullableType, operator, override, propertyDeclaration,
secondaryConstructor, setter, suspend */
