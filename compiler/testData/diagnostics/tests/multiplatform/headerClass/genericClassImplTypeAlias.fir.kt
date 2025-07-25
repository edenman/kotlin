// IGNORE_FIR_DIAGNOSTICS
// RUN_PIPELINE_TILL: FIR2IR
// DIAGNOSTICS: -ACTUAL_WITHOUT_EXPECT
// MODULE: m1-common
// FILE: common.kt

expect class C1
<!EXPECT_ACTUAL_IR_INCOMPATIBILITY{JVM}!>expect<!> interface C2<A>
<!EXPECT_ACTUAL_IR_INCOMPATIBILITY{JVM}!>expect<!> interface C3<B>
expect interface C4<D, E>
expect interface C5<F, G>
expect interface C6<H>
expect interface C7<I>
expect interface C8<J>
expect interface C9<K>
expect interface C10<L>

// MODULE: m2-jvm()()(m1-common)
// FILE: jvm.kt

class A<T : A<T>>
class B<T>

actual typealias C1 = String
<!ACTUAL_TYPE_ALIAS_TO_CLASS_WITH_DECLARATION_SITE_VARIANCE!>actual typealias <!EXPECT_ACTUAL_INCOMPATIBLE_TYPE_PARAMETER_VARIANCE!>C2<!><A> = List<String><!>
<!ACTUAL_TYPE_ALIAS_TO_CLASS_WITH_DECLARATION_SITE_VARIANCE!>actual typealias <!EXPECT_ACTUAL_INCOMPATIBLE_TYPE_PARAMETER_VARIANCE!>C3<!><B> = List<B><!>
actual typealias C4<D, E> = MutableMap<D, E>
<!ACTUAL_TYPE_ALIAS_WITH_COMPLEX_SUBSTITUTION!>actual typealias C5<F, G> = MutableMap<G, F><!>
<!ACTUAL_TYPE_ALIAS_WITH_COMPLEX_SUBSTITUTION!>actual typealias C51 = MutableMap<String, String><!>
<!ACTUAL_TYPE_ALIAS_WITH_COMPLEX_SUBSTITUTION!>actual typealias C52<F> = MutableMap<F, String><!>
<!ACTUAL_TYPE_ALIAS_WITH_COMPLEX_SUBSTITUTION!>actual typealias C53<T> = A<A<T>><!>
<!ACTUAL_TYPE_ALIAS_WITH_COMPLEX_SUBSTITUTION!>actual typealias C54<T> = B<List<String>><!>
actual typealias C6<H> = MutableList<H>
<!ACTUAL_TYPE_ALIAS_WITH_USE_SITE_VARIANCE!>actual typealias C7<I> = MutableList<out I><!>
<!ACTUAL_TYPE_ALIAS_WITH_USE_SITE_VARIANCE!>actual typealias C8<J> = MutableList<*><!>
<!ACTUAL_TYPE_ALIAS_WITH_USE_SITE_VARIANCE!>actual typealias C9<K> = MutableList<in K><!>

typealias Tmp<K> = MutableList<K>
<!ACTUAL_TYPE_ALIAS_NOT_TO_CLASS!>actual typealias C10<L> = Tmp<L><!>

/* GENERATED_FIR_TAGS: actual, classDeclaration, expect, inProjection, interfaceDeclaration, nullableType, outProjection,
starProjection, typeAliasDeclaration, typeAliasDeclarationWithTypeParameter, typeConstraint, typeParameter */
