// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// DIAGNOSTICS: -REDECLARATION -DUPLICATE_CLASS_NAMES

class C {
    <!FUNCTION_DECLARATION_WITH_NO_NAME!>fun ()<!> {

    }

    val<!SYNTAX!><!> : Int = 1

    class<!SYNTAX!><!> {}

    enum class<!SYNTAX!><!> {}
}

class C1<<!SYNTAX!>in<!>> {}

class C2(val<!SYNTAX!><!>) {}

/* GENERATED_FIR_TAGS: classDeclaration, enumDeclaration, functionDeclaration, integerLiteral, nestedClass, nullableType,
primaryConstructor, propertyDeclaration, typeParameter */
