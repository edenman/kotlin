// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
class A {
    companion object {
        enum class E { ENTRY }  // OK
    }
    
    inner class B {
        <!NESTED_CLASS_NOT_ALLOWED!>enum class E<!> { ENTRY }
    }
}

/* GENERATED_FIR_TAGS: classDeclaration, companionObject, enumDeclaration, enumEntry, inner, nestedClass,
objectDeclaration */
