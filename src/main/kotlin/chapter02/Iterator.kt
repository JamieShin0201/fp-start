package chapter02

class NotIterableList(
    private val list: List<Int>
)

class IterableList(
    private val list: List<Int>
) {
    operator fun iterator(): Iterator<Int> =
        object : Iterator<Int> {
            private var index = 0

            override fun hasNext(): Boolean = index < list.size

            override fun next(): Int {
                if (!hasNext()) throw NoSuchElementException()
                return list[index++]
            }
        }
}


fun main() {
//    iterator 가 없어서 for in 절을 사용할 수 없음
//    val list = NotIterableList(listOf(1, 2, 3))
//    for (i in list) {
//        println(i)
//    }

    val list = IterableList(listOf(1, 2, 3))
    val iterator = list.iterator()

    println(iterator.hasNext())
    println(iterator.next())

    for (i in iterator) {
        println(i)
    }

    println(iterator.hasNext())
}