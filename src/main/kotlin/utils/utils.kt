package utils


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
