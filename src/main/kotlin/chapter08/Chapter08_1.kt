package chapter08

import utils.L
import utils.curriedTake

fun <T> takeAll(iterable: Iterable<T>) = curriedTake<T>(Int.MAX_VALUE)(iterable)


fun <T, R> map(transform: (T) -> R) = { iterable: Iterable<T> ->
    takeAll(
        L.map(transform)(iterable)
    )
}

fun <T> filter(predicate: (T) -> Boolean) = { iterable: Iterable<T> ->
    takeAll(
        L.filter(predicate)(iterable)
    )

}


fun main() {
    val elements = listOf(1, 2, 3, 4, 5)
    println(
        map { a: Int -> a * a }(elements)
    )

    println(
        filter { a: Int -> a % 2 == 0 }(elements)
    )
}
