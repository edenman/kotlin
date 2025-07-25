// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// OPT_IN: kotlin.RequiresOptIn
// DIAGNOSTICS: -UNUSED_PARAMETER

import kotlin.experimental.ExperimentalTypeInference

inline fun <reified T, R> emptyFlow(crossinline transform: suspend (Array<T>) -> R): Flow1<R> =
    flow1 { emit(transform(emptyArray())) }

inline fun <reified T, R> emptyFlow(crossinline transform: (Array<T>) -> R): Flow1<R> =
    flowOf1(transform(emptyArray()))

fun <T> flowOf1(value: T): Flow1<T> = TODO()

@OptIn(ExperimentalTypeInference::class)
fun <T> flow1(block: suspend FlowCollector1<T>.() -> Unit): Flow1<T> = TODO()

interface FlowCollector1<in T> {
    suspend fun emit(value: T)
}

interface Flow1<out T>

/* GENERATED_FIR_TAGS: classReference, crossinline, functionDeclaration, functionalType, in, inline,
interfaceDeclaration, lambdaLiteral, nullableType, out, reified, suspend, typeParameter, typeWithExtension */
