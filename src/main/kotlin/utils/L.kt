package utils

import java.util.concurrent.CompletableFuture

fun go1(a: Any, f: ((Any) -> Any)) = when (a) {
    is CompletableFuture<*> -> a.thenApply(f).join() // TODO: 여기서 join 해야하나?..
    else -> f(a)
}

class L {

    companion object {
        fun range(l: Int) = Iterable {
            iterator {
                var i = -1
                while (++i < l) yield(i)
            }
        }

        fun <T, R> map(transform: (T) -> R) = { iterable: Iterable<T> ->
            Iterable {
                val iterator = iterable.iterator()
                iterator {
                    while (iterator.hasNext()) {
                        yield(transform(iterator.next()))
                    }
                }
            }
        }

        fun <T, R> map2(transform: (T) -> R) = { iterable: Iterable<T> ->
            Iterable {
                val iterator = iterable.iterator()
                iterator {
                    while (iterator.hasNext()) {
                        yield(
                            go1(iterator.next() as Any, transform as ((Any) -> Any))
                        )
                    }
                }
            }
        }

        fun <T> filter(predicate: (T) -> Boolean) = { iterable: Iterable<T> ->
            Iterable {
                val iterator = iterable.iterator()
                iterator {
                    while (iterator.hasNext()) {
                        val element = iterator.next()
                        if (predicate(element)) yield(element)
                    }
                }
            }
        }

        fun <T> filter2(predicate: (T) -> Boolean) = { iterable: Iterable<T> ->
            Iterable {
                val iterator = iterable.iterator()
                iterator {
                    while (iterator.hasNext()) {
                        val element = iterator.next() as Any
                        val result = go1(element, predicate as ((Any) -> Any))
                        if (result as Boolean) {
                            if (element is CompletableFuture<*>) yield(element.join())
                            else yield(element)
                        }
                    }
                }
            }
        }

        fun <T, R> reduce(f: (R, T) -> R, acc: R) = { iterable: Iterable<T> ->
            Iterable {
                val iterator = iterable.iterator()
                iterator {
                    while (iterator.hasNext()) {
                        yield(f(acc, iterator.next()))
                    }
                }
            }
        }

        fun <T> flatten(iterable: Iterable<T>) =
            Iterable {
                val iterator = iterable.iterator()
                iterator {
                    while (iterator.hasNext()) {
                        val element = iterator.next()
                        if (element is Iterable<*>) {
                            for (e in element) yield(e)
                        } else yield(element)
                    }
                }
            }

        fun <T> deepFlat(iterable: Iterable<T>): Iterable<T> {
            return Iterable {
                val iterator = iterable.iterator()
                iterator {
                    while (iterator.hasNext()) {
                        val element = iterator.next()
                        if (element is Iterable<*>) {
                            val iterable = deepFlat(element)
                            for (e in iterable) yield(e as T)
                        } else yield(element)
                    }
                }
            }
        }

        fun <T, R> flatMap(transform: (T) -> R) = { iterable: Iterable<T> ->
            map(transform)(
                deepFlat(iterable)
            )
        }
    }
}
