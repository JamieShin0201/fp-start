package chapter03

// 제너레이터, 이터레이터
// - 제너레이터: 이터레이터이자 이터러블을 생성하는 함수
val sequence = sequence {
    yield(1)
    yield(2)
    yield(3)
}

fun infinity(start: Int = 0) = sequence {
    var start = start
    while (true) yield(start++)
}

fun limit(l: Int, iterator: Sequence<Int>) = sequence {
    for (i in iterator) {
        yield(i)
        if (i == l) return@sequence
    }
}

fun odds(l: Int) = sequence {
    for (i in limit(l, infinity(1))) {
        if (i % 2 != 0) yield(i)
    }
}

fun main() {
    val iterator = sequence.iterator()
    println(iterator.next())
    for (i in iterator) {
        println(i)
    }
    println()

    val a = infinity(3).take(3)
    for (i in a) {
        println(i)
    }

    println()
    odds(10).forEach { println(it) }
}
