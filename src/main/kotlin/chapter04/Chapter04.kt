package chapter04

data class Product(
    val name: String,
    val price: Int
)

val products = listOf(
    Product(name = "반팔티", price = 15000),
    Product(name = "긴팔티", price = 20000),
    Product(name = "핸드폰케이스", price = 15000),
    Product(name = "후드티", price = 30000),
    Product(name = "바지", price = 25000)
)

fun <T, R> map(transform: (T) -> R, iterable: Iterable<T>): Iterable<R> {
    val newList = mutableListOf<R>()
    for (element in iterable) {
        newList.add(transform(element))
    }

    return newList
}

fun <T> filter(predicate: (T) -> Boolean, iterable: Iterable<T>): Iterable<T> {
    val newList = mutableListOf<T>()
    for (element in iterable) {
        if (predicate(element)) newList.add(element)
    }

    return newList
}

fun <T, R> reduce(accumulate: (R, T) -> R, init: R, iterable: Iterable<T>): R {
    var acc = init
    for (element in iterable) {
        acc = accumulate(acc, element)
    }
    return acc
}

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


