package utils


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
    }
}
