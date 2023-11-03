package chapter04

import utils.*


val iterable = Iterable {
    iterator {
        yield(1)
        yield(2)
        yield(3)
    }
}

val map = mapOf("a" to 10, "b" to 20, "c" to 30)

fun main() {
    println(map({ e -> e.name }, products))
    println(map({ e -> e.price }, products))
    println(map({ e -> e * e }, iterable))
    println(map({ (key, value) -> key to value * value }, map.entries))

    println(filter({ it.price < 20000 }, products))
    println(filter({ it < 3 }, iterable))

    val add = { a: Int, b: Int -> a + b }
    println(reduce(add, 0, listOf(1, 2, 3, 4, 5)))

    println(
        reduce(
            { totalPrice: Int, product: Product -> totalPrice + product.price },
            0,
            products
        )
    )

    println(
        reduce(
            { totalPrice: Int, price: Int -> totalPrice + price },
            0,
            map(
                { it.price },
                products
            )
        )
    )


    println(
        reduce(
            { totalPrice: Int, price: Int -> totalPrice + price },
            0,
            map(
                { it.price },
                filter(
                    { it.price > 15000 },
                    products
                )
            )
        )
    )

    println(
        reduce(
            { totalPrice: Int, price: Int -> totalPrice + price },
            0,
            map(
                { it.price },
                filter(
                    { it.price <= 15000 },
                    products
                )
            )
        )
    )
}


