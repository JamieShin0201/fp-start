package chapter07

import utils.L
import utils.curriedFilter
import utils.curriedMap
import utils.curriedTake
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main() {
    println("========== map ==========")
    println(
        curriedTake<Int>(5)(
            curriedMap<Int, Int> { it * 2 }(L.range(10000))
        )
    )
    println(
        "E: " + measureTime {
            curriedTake<Int>(5)(
                curriedMap<Int, Int> { it * 2 }(L.range(10000))
            )

        }
    )
    println(
        curriedTake<Int>(5)(
            L.map<Int, Int> { it * 2 }(L.range(10000))
        )
    )
    println(
        "L: " + measureTime {
            curriedTake<Int>(5)(
                L.map<Int, Int> { it * 2 }(L.range(10000))
            )
        }
    )

    println()
    println("========== filter ==========")
    println(
        curriedTake<Int>(5)(
            curriedFilter<Int> { it % 2 == 0 }(L.range(10000))
        )
    )
    println(
        "E: " + measureTime {
            curriedTake<Int>(5)(
                curriedFilter<Int> { it % 2 == 0 }(L.range(10000))
            )

        }
    )
    println(
        curriedTake<Int>(5)(
            L.filter<Int> { it % 2 == 0 }(L.range(10000))
        )
    )
    println(
        "L: " + measureTime {
            curriedTake<Int>(5)(
                L.filter<Int> { it % 2 == 0 }(L.range(10000))
            )
        }
    )
}
