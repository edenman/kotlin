// RUN_PIPELINE_TILL: FRONTEND
// ISSUE: KT-25747
fun test1() {
    val nullableString: String? = ""
    val savedSmartCastResult = nullableString != null
    if (savedSmartCastResult) {
        nullableString<!UNSAFE_CALL!>.<!>length
    }
}

fun test2() {
    var nullableAny: Any? = ""
    val savedSmartCastResult = nullableAny is String
    nullableAny = 10
    if (savedSmartCastResult) {
        nullableAny.<!UNRESOLVED_REFERENCE!>length<!>
    }
}

class A {
    val a: String? = ""
}
fun test3(a: A){
    val savedSmartCastResult = a.a != null
    if(savedSmartCastResult) {
        a.a<!UNSAFE_CALL!>.<!>length
    }
}

fun test4() {
    val nullableAny: Any? = ""
    val savedSmartCastResult = (nullableAny!= null && nullableAny is String?)
    if(savedSmartCastResult) {
        nullableAny.<!UNRESOLVED_REFERENCE!>length<!>
    }
}

/* GENERATED_FIR_TAGS: andExpression, assignment, classDeclaration, equalityExpression, functionDeclaration,
ifExpression, integerLiteral, isExpression, localProperty, nullableType, propertyDeclaration, smartcast, stringLiteral */
