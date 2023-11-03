package utils


// 코드를 값으로 다루어 표현력 높이기
// go, pipe
fun go(vararg args: (Int) -> Int) =
    curriedReduce({ acc, f: (Int) -> Int -> f(acc) }, 0)(args.toList())

fun pipe(vararg args: (Int) -> Int): (Int) -> Int {
    val first = args[0]
    val rest = args.slice(1 until args.size)

    return { element ->
        first(element) + go(*rest.toTypedArray())
    }
}

fun <T, R> curriedMap(transform: (T) -> R) =
    { iterable: Iterable<T> ->
        val newList = mutableListOf<R>()
        for (element in iterable) {
            newList.add(transform(element))
        }

        newList
    }

fun <T> curriedFilter(predicate: (T) -> Boolean) =
    { iterable: Iterable<T> ->
        val newList = mutableListOf<T>()
        for (element in iterable) {
            if (predicate(element)) newList.add(element)
        }

        newList
    }

fun <T, R> curriedReduce(accumulate: (R, T) -> R, init: R) =
    { iterable: Iterable<T> ->
        var acc = init
        for (element in iterable) {
            acc = accumulate(acc, element)
        }
        acc
    }


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
