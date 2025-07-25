// RUN_PIPELINE_TILL: FRONTEND
// CHECK_TYPE
interface A {
    val x: Int

    val z: Comparable<*>
}

open class B {
    open var y = ""

    open val z: CharSequence = ""
}

class C : B(), A {
    override val x
        get() = 1

    override var y
        get() = super.y
        set(value) {
            value checkType { _<String>() }
        }

    override var z
        get() = ""
        set(value) {
            value checkType { _<String>() }
        }
}

fun foo(c: C) {
    c.x checkType { _<Int>() }
    c.y checkType { _<String>() }
    c.z checkType { _<String>() }

    c.y = ""
    c.y = <!ASSIGNMENT_TYPE_MISMATCH!>1<!>

    c.z = ""
    c.z = <!ASSIGNMENT_TYPE_MISMATCH!>1<!>
}

/* GENERATED_FIR_TAGS: assignment, classDeclaration, funWithExtensionReceiver, functionDeclaration, functionalType,
getter, infix, integerLiteral, interfaceDeclaration, lambdaLiteral, nullableType, override, propertyDeclaration, setter,
starProjection, stringLiteral, superExpression, typeParameter, typeWithExtension */
