package chapter07

import utils.L
import utils.curriedTake
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime


val range = { l: Int ->
    var i = -1
    var list = mutableListOf<Int>()
    while (++i < l) {
        list.add(i)
    }
    list
}


@OptIn(ExperimentalTime::class)
fun main() {
    val add = { a: Int, b: Int -> a + b }

    val list = range(10)
    println(list)

    val lList = L.range(10).toList()
    println(lList)

    println(
        "E: " + measureTime { range(10000) }
    )

    println(
        "L: " + measureTime { L.range(10000) }
    )

    println(
        measureTime { L.range(10000).toList() } // 실제 값이 사용될 때 리스트가 초기화됨.
    )

    println(
        "E: " + measureTime { curriedTake<Int>(10)(range(100000)) }
    )
    curriedTake<Int>(10)(L.range(100000))

    println(
        "L: " + measureTime { curriedTake<Int>(10)(L.range(100000)) }
    )
}

