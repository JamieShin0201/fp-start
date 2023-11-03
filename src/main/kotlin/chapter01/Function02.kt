package chapter01


// 일급 함수
// - 함수가 값으로 다뤄질 수 있다.

// 고차 함수
// - 함수를 값으로 다루는 함수.

// 함수를 인자로 받아서 실행하는 함수
// apply1
// times

val apply1 = { f: (Int) -> Int -> f(1) }
val add2 = { a: Int -> a + 2 }

val times = { f: (Int) -> Unit, n: Int ->
    for (i in 1..n) {
        f(i)
    }
}

// 함수를 만들어 리턴하는 함수 (클로저를 만들어 리턴하는 함수)
// addMaker

val addMaker = { a: Int -> { b: Int -> a + b } }
private val add20 = addMaker(20)

fun main() {
    println(apply1(add2))
    println(apply1 { a: Int -> a - 1 })

    times({ println("test") }, 3)

    times({ println(it + 10) }, 3)

    println(add10(5))
    println(add10(10))
}
