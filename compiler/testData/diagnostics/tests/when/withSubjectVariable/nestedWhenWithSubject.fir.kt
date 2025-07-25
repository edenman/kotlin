// RUN_PIPELINE_TILL: BACKEND
// LANGUAGE: +VariableDeclarationInWhenSubject
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE

fun foo() {}
fun <T> bar(x: T, y: T) {}

fun test1() {
    when (1) {
        1 ->
            when (val y = 2) {
                2 -> foo()
            }
    }
}

fun test2() {
    when (val x = 1) {
        1 ->
            when (val y = 2) {
                2 -> foo()
            }
    }
}

fun test3() {
    when (val x = 1) {
        1 ->
            when (val x = 2) {
                2 -> foo()
            }
    }
}

fun test4() {
    when (val x = 1) {
        1 ->
            when (val y = 2) {
                2 -> bar(x, y)
            }
    }
}

/* GENERATED_FIR_TAGS: equalityExpression, functionDeclaration, integerLiteral, localProperty, nullableType,
propertyDeclaration, typeParameter, whenExpression, whenWithSubject */
