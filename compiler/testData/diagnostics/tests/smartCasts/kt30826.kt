// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// Issue: KT-30826

interface I1
interface I2 {
    fun foo() {}
}

class A : I1, I2

fun foo(x: I1?) {
    var y = x
    y as I2
    val bar = {
        <!SMARTCAST_IMPOSSIBLE!>y<!>.foo() // NPE in NI, smartcast impossible in OI
    }
    y = null
    bar()
}

/* GENERATED_FIR_TAGS: asExpression, assignment, classDeclaration, functionDeclaration, interfaceDeclaration,
intersectionType, lambdaLiteral, localProperty, nullableType, propertyDeclaration, smartcast */
