// RUN_PIPELINE_TILL: FRONTEND
// ISSUE: KT-58310
// LANGUAGE: -CheckLambdaAgainstTypeVariableContradictionInResolution

class Inv<T>

fun <T> foo(box: Inv<T>, value: T) {}
fun <T> foo(box: Inv<T>, value: () -> T) {}
fun <T> bar(value: T, box: Inv<T>) {}
fun <T> bar(value: () -> T, box: Inv<T>) {}
fun <T> Inv<T>.qux(value: T) {}
fun <T> Inv<T>.qux(value: () -> T) {}

fun test(box: Inv<String>) {
    <!OVERLOAD_RESOLUTION_AMBIGUITY!>foo<!>(box) { "hello" }
    <!OVERLOAD_RESOLUTION_AMBIGUITY!>bar<!>({ "hello" }, box)
    box.<!OVERLOAD_RESOLUTION_AMBIGUITY!>qux<!> { "hello" }
}

fun testAny(box: Inv<Any>) {
    <!OVERLOAD_RESOLUTION_AMBIGUITY!>foo<!>(box) { "hello" }
    <!OVERLOAD_RESOLUTION_AMBIGUITY!>bar<!>({ "hello" }, box)
    box.<!OVERLOAD_RESOLUTION_AMBIGUITY!>qux<!> { "hello" }
}

fun testFunctionType(box: Inv<() -> Any?>) {
    <!OVERLOAD_RESOLUTION_AMBIGUITY!>foo<!>(box) { "hello" }
    <!OVERLOAD_RESOLUTION_AMBIGUITY!>bar<!>({ "hello" }, box)
    box.<!OVERLOAD_RESOLUTION_AMBIGUITY!>qux<!> { "hello" }
}

// Check error when there is only one candidate
fun <T> noOverloads(box: Inv<T>, value: T) {}

fun testError(box: Inv<String>) {
    noOverloads(box) <!ARGUMENT_TYPE_MISMATCH("Function0<ERROR CLASS: Unknown return lambda parameter type>; String"), CANNOT_INFER_IT_PARAMETER_TYPE!>{ "hello" }<!>
}

fun testOk(box1: Inv<Any>, box2: Inv<() -> Any?>) {
    noOverloads(box1) { "hello" }
    noOverloads(box2) { "hello" }
}

fun <T> twoBoxes(box: Inv<T>, box2: Inv<T>, value: T) {}

fun testContradiction(box1: Inv<Any>, box2: Inv<String>) {
    twoBoxes(box1, <!ARGUMENT_TYPE_MISMATCH!>box2<!>) { "" }
}

/* GENERATED_FIR_TAGS: classDeclaration, funWithExtensionReceiver, functionDeclaration, functionalType, lambdaLiteral,
nullableType, stringLiteral, typeParameter */
