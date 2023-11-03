package chapter01


// 평가: 코드가 계산되어 값을 만드는 것
// 일급
// - 값으로 다룰 수 있음.
// - 변수에 담을 수 있음.
// - 함수의 인자로 사용될 수 있음.
// - 함수의 결과로 사용될 수 있음.

val a = 10
val add10 = { a: Int -> a + 10 }
val result = add10(10)

// 일급 함수
// - 함수를 값으로 다룰 수 있다.
// - 조합성과 추상화의 도구

val add5 = { a: Int -> a + 5 }

val f1 = { { 1 } }
val f2 = f1()

fun main() {
    println(result)

    println(add5)
    println(add5(5))

    println(f1)
    println(f2)
    println(f2())
}



