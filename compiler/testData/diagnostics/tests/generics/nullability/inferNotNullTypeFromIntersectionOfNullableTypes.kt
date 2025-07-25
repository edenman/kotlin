// RUN_PIPELINE_TILL: BACKEND

fun simpleTypeAndNumberType(b: Comparable<*>?) {
    if (b is Byte?) {
        <!DEBUG_INFO_SMARTCAST!>b<!>!!.dec()
    }
}

fun <T> typeParmeterAndNumberType(b: T?) {
    if (b is Byte?) {
        b!!.dec()
    }
}

fun anyAndNumberType(b: Any?) {
    if (b is Byte?) {
        <!DEBUG_INFO_SMARTCAST!>b<!>!!.dec()
    }
}

fun comparableAndNumberType(b: Comparable<Byte>?) {
    if (b is Byte?) {
        <!DEBUG_INFO_SMARTCAST!>b<!>!!.dec()
    }
}

object SeparateTypes {
    interface A
    interface B {
        fun foo() {}
    }

    fun separate(a: A?) {
        if (a is B?) {
            a!!.foo()
        }
    }
}

/* GENERATED_FIR_TAGS: checkNotNullCall, functionDeclaration, ifExpression, interfaceDeclaration, intersectionType,
isExpression, nestedClass, nullableType, objectDeclaration, smartcast, starProjection, typeParameter */
