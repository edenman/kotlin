// RUN_PIPELINE_TILL: FRONTEND
// WITH_NEW_INFERENCE
// DUMP_CFG: LEVELS
val (a1, a2) = A()
val (b1: Int, b2: Int) = <!COMPONENT_FUNCTION_RETURN_TYPE_MISMATCH!>A()<!>
val (c1) = <!COMPONENT_FUNCTION_MISSING, UNRESOLVED_REFERENCE!>unresolved<!>

<!WRONG_MODIFIER_TARGET!>private<!> val (d1) = A()

val (e1, _) = A()

a1
a2
e1

class A {
    operator fun component1() = 1
    operator fun component2() = ""
}

/* GENERATED_FIR_TAGS: classDeclaration, destructuringDeclaration, functionDeclaration, init, integerLiteral,
localProperty, operator, propertyDeclaration, stringLiteral, unnamedLocalVariable */
