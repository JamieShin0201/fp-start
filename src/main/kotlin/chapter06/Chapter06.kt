package chapter06

import utils.Product
import utils.curriedMap
import utils.curriedReduce
import utils.products

fun main() {
    val add = { a: Int, b: Int -> a + b }

    val sum = curriedReduce(
        add, 0
    )(
        curriedMap<Product, Int> { it.price }(products)
    )

    fun <T, R> sum(transform: (T) -> R) =
        { iterable: Iterable<T> ->
            curriedReduce(
                { a: Int, b: T -> a + transform(b) as Int }, 0
            )(iterable)
        }

    val totalPrice = sum { p: Product -> p.price }
    println(totalPrice(products))

    val total = sum { p: Int -> p }
    println(total(listOf(1, 2, 3, 4, 5)))
}
