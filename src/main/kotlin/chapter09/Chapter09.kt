package chapter09

import java.util.concurrent.CompletableFuture

fun add10(a: Int, callback: ((Int) -> Int)): Int {
    Thread.sleep(1000)
    return callback(a + 10)
}

fun add20(a: Int) = CompletableFuture.supplyAsync {
    Thread.sleep(1000)
    a + 20
}

fun delay100(a: Int) = CompletableFuture.supplyAsync {
    Thread.sleep(100)
    a
}

fun go1(a: Any, callback: (Any) -> Any) = when (a) {
    is CompletableFuture<*> -> a.thenApply(callback).join()
    else -> callback(a)
}

fun add5(a: Any): Any {
    a as Int
    return a + 5
}

fun main() {
    // 1. callback
//    val a = add10(5) { a: Int ->
//        add10(a) { b: Int ->
//            add10(b) {
//                b
//            }
//        }
//    }
//    println(a)

    // 2. CompletableFuture
//    val future = add20(10)
//        .thenApply {
//            add20(it).join()
//        }
//        .thenAccept {
//            println(it)
//        }
//
//    future.join()

    // 3. 일급 활용
//    val a = 10
//    val add5: ((Any) -> Any) = { a: Any ->
//        a as Int
//        a + 5
//    }
//
//    println(
//        go1(go1(a, add5), add5)
//    )
//
//    println(
//        go1(go1(delay100(a), add5), add5)
//    )

    // 4. Composition
//    val g = { a: Int -> a + 1 }
//    val f = { a: Int -> a * a }
//
//    listOf(1).map(g).map(f).forEach(::println)
//    listOf<Int>().map(g).map(f).forEach(::println)
//
//    CompletableFuture.supplyAsync { 2 }
//        .thenApply(g)
//        .thenApply(f)
//        .thenAccept(::println)
//
//    CompletableFuture.supplyAsync {
//        Thread.sleep(100)
//        2
//    }
//        .thenApply(g)
//        .thenApply(f)
//        .thenAccept(::println)
//        .join()

    // 5. Kleisli Composition
    val users = listOf(User(1, "a"), User(2, "b"), User(3, "c"))
    val getUserById = { id: Long ->
        users.find { it.id == id } ?: throw Exception("없어요.")
    }
    val g = getUserById
    val f = { u: Any ->
        u as User
        u.name
    }

    val fg = { id: Long ->
        CompletableFuture.supplyAsync { id }
            .thenApply(g)
            .thenApply(f)
            .exceptionally { a -> a.message }
    }

    fg(2L)
        .thenApply(::println)
        .join()

    fg(4L)
        .thenApply(::println)
        .join()

    CompletableFuture.supplyAsync { 1 }
        .thenApply { it + 10 }
        .thenApply { it + 30 }
        .thenAccept { println(it) }
        .thenApply { throw Exception("XXXX") }
        .thenAccept { println("출력 안됨: 예외 발생!!!!!") }
        .exceptionally {
            println(it.message)
            null
        }.join()
}

//Promise.resolve(1),
//a => a + 10,
//a => Promise.reject('error~~'),
//a => console.log('----'),
//a => a + 1000,
//a => a + 10000,
//log).catch(a => console.log(a));

data class User(
    val id: Long,
    val name: String,
)
