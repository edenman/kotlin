// RUN_PIPELINE_TILL: FRONTEND
fun foo(arg: Int?): Int {
    var i = arg
    if (i != null && i++ == 5) {
        return i-- + i
    }
    return 0
}

operator fun Long?.inc() = this?.let { it + 1 }

fun bar(arg: Long?): Long {
    var i = arg
    if (i++ == 5L) {
        return i<!UNSAFE_CALL!>--<!> <!UNSAFE_OPERATOR_CALL!>+<!> i
    }
    if (i++ == 7L) {
        return i++ <!UNSAFE_OPERATOR_CALL!>+<!> <!ARGUMENT_TYPE_MISMATCH!>i<!>
    }
    return 0L
}

/* GENERATED_FIR_TAGS: additiveExpression, andExpression, assignment, equalityExpression, funWithExtensionReceiver,
functionDeclaration, ifExpression, incrementDecrementExpression, integerLiteral, lambdaLiteral, localProperty,
nullableType, operator, propertyDeclaration, safeCall, smartcast, thisExpression */
