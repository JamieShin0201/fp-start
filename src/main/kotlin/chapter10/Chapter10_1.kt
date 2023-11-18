package chapter10

import utils.L
import utils.NopException
import utils.curriedReduce2
import utils.curriedTake2
import java.util.concurrent.CompletableFuture


private fun future(value: Int): CompletableFuture<Int> = CompletableFuture.supplyAsync {
    Thread.sleep(100)
    value
}

fun main() {
    val future1 = future(1)
    val future2 = future(2)
    val future3 = future(3)
    val future4 = CompletableFuture.failedFuture<NopException>(NopException())

    println("====== map ======")
    val list = listOf(future1, future2, future3)
    L.map2<Any, Any> { it as Int + 1 }(list)
        .forEach(::println)

    println()
    println("====== filter ======")
    L.filter2<Any> { it as Int % 2 == 0 }(list)
        .forEach(::println)


    println()
    println("====== filter, map ======")
    L.filter2<Any> { it as Int % 2 == 0 }(
        L.map2<Any, Any> { it as Int + 1 }(list)
    ).forEach(::println)

    println()
    L.filter2<Any> { it as Int % 2 == 0 }(
        L.map2<Any, Any> {
            CompletableFuture.supplyAsync {
                Thread.sleep(100)
                it as Int + 1
            }
        }(list)
    ).forEach(::println)

    println()
    println("====== take ======")
    println(
        curriedTake2<Any>(2)(
            L.map2<Any, Any> {
                CompletableFuture.supplyAsync {
                    Thread.sleep(100)
                    it as Int + 1
                }
            }(list)
        )
    )

    println()
    println("====== reduce ======")
    println(
        curriedReduce2<Any, Any>({ a: Any, b: Any ->
            a as Int + b as Int
        }, 1)(
            L.map2<Any, Any> {
                CompletableFuture.supplyAsync {
                    Thread.sleep(100)
                    it as Int + 1
                }
            }(list)
        )
    )

    // nop 지원
    val list2 = listOf(future1, future2, future3, future4)
    println()
    println("====== take with nop ======")
    println(
        curriedTake2<Any>(4)(list2)
    )

    println()
    println("====== reduce with nop ======")
    println(
        curriedReduce2<Any, Any>({ a: Any, b: Any ->
            a as Int + b as Int
        }, 0)(list2)
    )
}

