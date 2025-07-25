// RUN_PIPELINE_TILL: FRONTEND
// ISSUE: KT-64635
import kotlin.reflect.KProperty

class Context

interface MyReadOnlyProperty<in T, out V> {
    operator fun getValue(thisRef: T, property: KProperty<*>): V
}

open class NodeHolder {
    operator fun ((Context).() -> Unit).provideDelegate(
        thisRef: Any?,
        prop: KProperty<*>
    ): MyReadOnlyProperty<Any?, Unit> = TODO()
}

class SubClass1 : NodeHolder() {
    val foo: (Context).() -> Unit = {}
    val x by foo
}

class SubClass2 : NodeHolder() {
    val x <!DELEGATE_SPECIAL_FUNCTION_MISSING!>by<!> {}
}

fun main() {
    val my_holder_works =
        object : NodeHolder() {
            val foo: (Context).() -> Unit = {}
            val x by foo
        }

    val my_holder_bad =
        object : NodeHolder() {
            val x <!DELEGATE_SPECIAL_FUNCTION_MISSING!>by<!> {}
        }
}

/* GENERATED_FIR_TAGS: anonymousObjectExpression, classDeclaration, funWithExtensionReceiver, functionDeclaration,
functionalType, in, interfaceDeclaration, lambdaLiteral, localProperty, nullableType, operator, out, propertyDeclaration,
propertyDelegate, starProjection, typeParameter, typeWithExtension */
