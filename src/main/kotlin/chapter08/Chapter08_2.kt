package chapter08

import utils.L

fun main() {
    val list = listOf(1, 2, 3, listOf(4, 5, 6), 7, listOf(9))

    println(
        takeAll(
            L.flatten(list)
        )
    )

    val deepList = listOf(1, 2, 3, listOf(4, 5, listOf(6, listOf(7))), listOf(8, 9))
    println(
        takeAll(
            L.deepFlat(deepList)
        )
    )

    println(
        takeAll(
            L.flatMap<Any, Int> { a -> a as Int * a }(deepList)
        )
    )
}
