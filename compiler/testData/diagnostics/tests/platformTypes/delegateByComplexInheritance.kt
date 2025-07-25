// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// FILE: A.java

import java.util.*;

public interface A {
    <R> void foo();
}

// FILE: B.java

public interface B extends A {}

// FILE: k.kt

class C(x: A) : A by x, B {}

/* GENERATED_FIR_TAGS: classDeclaration, inheritanceDelegation, javaType, primaryConstructor */
