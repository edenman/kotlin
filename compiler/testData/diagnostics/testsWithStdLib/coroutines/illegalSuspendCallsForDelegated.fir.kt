// RUN_PIPELINE_TILL: FRONTEND
fun bar(d: Delegate): String {
    val x: String by <!ILLEGAL_SUSPEND_FUNCTION_CALL!>d<!>
    return x
}

class Delegate {
    suspend <!INAPPLICABLE_OPERATOR_MODIFIER!>operator<!> fun getValue(thisRef: Any?, property: Any?): String = ""
}

/* GENERATED_FIR_TAGS: classDeclaration, functionDeclaration, localProperty, nullableType, operator, propertyDeclaration,
propertyDelegate, stringLiteral, suspend */
