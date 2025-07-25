// RUN_PIPELINE_TILL: BACKEND
interface A {
    fun foo()
}

interface C: A
interface B: A

fun <T> test(x: T) where T : C?, T : B? {
    x?.foo()
    if (x != null) {
        x.foo()
    }
}

/* GENERATED_FIR_TAGS: dnnType, equalityExpression, functionDeclaration, ifExpression, interfaceDeclaration,
nullableType, safeCall, smartcast, typeConstraint, typeParameter */
