// RUN_PIPELINE_TILL: FRONTEND
// CHECK_TYPE
open class BaseOuter {
    protected fun foo() = 1
    protected fun bar() { }
}

class Foo(var base: BaseOuter)

fun BaseOuter.foo(): String = ""

class Derived : BaseOuter() {
    fun test(foo: Foo) {
        if (foo.base is Derived) {
            foo.base.foo() checkType { _<String>() } // Resolved to extension
            foo.base.<!INVISIBLE_REFERENCE!>bar<!>()
        }
    }
}

/* GENERATED_FIR_TAGS: classDeclaration, funWithExtensionReceiver, functionDeclaration, functionalType, ifExpression,
infix, integerLiteral, isExpression, lambdaLiteral, nullableType, primaryConstructor, propertyDeclaration, smartcast,
stringLiteral, typeParameter, typeWithExtension */
