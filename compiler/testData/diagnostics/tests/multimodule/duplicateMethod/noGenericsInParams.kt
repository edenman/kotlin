// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNNECESSARY_SAFE_CALL -SAFE_CALL_WILL_CHANGE_NULLABILITY
// MODULE: m1
// FILE: a.kt
package p

public interface B {
    public fun foo(a: Int, b: String): B?
}

// MODULE: m2(m1)
// FILE: b.kt
package p

public interface C : B {
    override fun foo(a: Int, b: String): B?

}

// MODULE: m3
// FILE: b.kt
package p

public interface B {
    public fun foo(a: Int, b: String): B?
}

// MODULE: m4(m3, m2)
// FILE: c.kt
import p.*

fun test(b: B?) {
    if (b is C) {
        b?.foo(1, "")
    }
}

/* GENERATED_FIR_TAGS: functionDeclaration, ifExpression, integerLiteral, interfaceDeclaration, isExpression,
nullableType, override, safeCall, smartcast, stringLiteral */
