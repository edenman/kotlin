// RUN_PIPELINE_TILL: BACKEND
fun <T, R> apply(x: T, f: (T) -> R): R = f(x)

fun foo(i: Int) {}
fun foo(s: String) {}

val x1 = apply(1, ::foo)
val x2 = apply("hello", ::foo)

/* GENERATED_FIR_TAGS: callableReference, functionDeclaration, functionalType, integerLiteral, nullableType,
propertyDeclaration, stringLiteral, typeParameter */
