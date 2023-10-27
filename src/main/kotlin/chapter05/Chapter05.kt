package chapter05

import utils.Product
import utils.curriedFilter
import utils.curriedMap
import utils.curriedReduce
import utils.go
import utils.pipe
import utils.products


fun main() {
    val add = { a: Int, b: Int -> a + b }
    println(
        curriedReduce(add, 0)(
            curriedMap<Product, Int> { it.price }(
                curriedFilter<Product> { it.price > 15000 }(
                    products
                )
            )
        )
    )

    println(
        go(
            { a: Int -> a + 10 },
            { a: Int -> a + 100 }
        )
    )

    println(
        pipe(
            { a: Int -> a + 10 },
            { a: Int -> a + 100 }
        )(1)
    )

    // curry
    println()
    fun curry(f: (Int, Int) -> Int) =
        { a: Int ->
            { b: Int ->
                f(a, b)
            }
        }

    val multi = curry { a: Int, b: Int -> a * b }
    println(multi(3)(2))

    val multi3 = multi(3)
    println(multi3(10))
    println(multi3(5))
    println(multi3(3))

    // 함수 조합으로 함수 만들기
    // TODO: 타입 언어, 제네릭 erase 이슈로 인해 어려움..
}
