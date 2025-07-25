// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// FILE: KotlinFile.kt
fun foo(javaInterface: JavaInterface) {
    val value = javaInterface.compute { "" }
    value<!UNSAFE_CALL!>.<!>length
}

// FILE: JavaInterface.java
import org.jetbrains.annotations.*;

public interface JavaInterface {
    @Nullable
    <T> String compute(@NotNull Provider<T> provider);
}

// FILE: Provider.java
public interface Provider<T> {
    public T compute();
}

/* GENERATED_FIR_TAGS: flexibleType, functionDeclaration, javaFunction, javaType, lambdaLiteral, localProperty,
nullableType, propertyDeclaration, samConversion, stringLiteral */
