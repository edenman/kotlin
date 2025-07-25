// RUN_PIPELINE_TILL: FRONTEND
// DIAGNOSTICS: -DEBUG_INFO_SMARTCAST
fun bar(x: Int): Int = x + 1

fun foo() {
    val x: Int? = null

    bar(1 + (if (x == null) 0 else x))
    bar(<!ARGUMENT_TYPE_MISMATCH!>if (x == null) x else x<!>)
    if (x != null) bar(x + x/(x-x*x))
}

/* GENERATED_FIR_TAGS: additiveExpression, equalityExpression, functionDeclaration, ifExpression, integerLiteral,
localProperty, multiplicativeExpression, nullableType, propertyDeclaration, smartcast */
