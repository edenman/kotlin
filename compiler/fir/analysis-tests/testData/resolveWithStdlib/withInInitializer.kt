// RUN_PIPELINE_TILL: BACKEND
class First(val member: Int)

class Second {
    val list = listOf(1, 2, 3, "")

    val data = First(42)

    val test = with(data) {
        list.filterIsInstance<Int>().filter {
            it == member
        }
    }
}

/* GENERATED_FIR_TAGS: classDeclaration, equalityExpression, integerLiteral, intersectionType, lambdaLiteral,
primaryConstructor, propertyDeclaration, stringLiteral */
