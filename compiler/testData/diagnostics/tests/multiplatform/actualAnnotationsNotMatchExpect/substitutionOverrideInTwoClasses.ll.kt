// LL_FIR_DIVERGENCE
// Not a real LL divergence, it's just tiered runners reporting errors from `BACKEND`
// LL_FIR_DIVERGENCE
// RUN_PIPELINE_TILL: BACKEND
// MODULE: m1-common
// FILE: common.kt
annotation class Ann

expect class WithAnn {
    @Ann
    fun foo(p: String)
}

expect class WithoutAnn {
    fun foo(p: String)
}

// MODULE: m1-jvm()()(m1-common)
// FILE: jvm.kt
abstract class Parent<T> {
    open fun foo(p: T) {}
}

abstract class Intermediate : Parent<String>()

actual class <!ACTUAL_ANNOTATIONS_NOT_MATCH_EXPECT!>WithAnn<!> : Intermediate()

actual class WithoutAnn : Intermediate()

/* GENERATED_FIR_TAGS: actual, annotationDeclaration, classDeclaration, expect, functionDeclaration, nullableType,
typeParameter */
