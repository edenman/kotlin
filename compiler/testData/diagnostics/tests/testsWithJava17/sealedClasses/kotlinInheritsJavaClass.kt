// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// ISSUE: KT-41215

// FILE: Base.java
public sealed class Base permits A, B {}

// FILE: A.java
public final class A extends Base {}

// FILE: B.kt

class B : <!CLASS_INHERITS_JAVA_SEALED_CLASS!>Base<!>()

/* GENERATED_FIR_TAGS: classDeclaration, javaType */
