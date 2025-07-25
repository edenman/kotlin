// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
interface IFoo {
    fun foo(i: Int): Int
    infix fun bar(i: Int): Int
}

infix fun IFoo.foo(i: Int) = i
infix fun IFoo.<!EXTENSION_SHADOWED_BY_MEMBER!>bar<!>(i: Int) = i

/* GENERATED_FIR_TAGS: funWithExtensionReceiver, functionDeclaration, infix, interfaceDeclaration */
