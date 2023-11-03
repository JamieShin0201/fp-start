package utils

data class Product(
    val name: String,
    val price: Int
)

val products = listOf(
    Product(name = "반팔티", price = 15000),
    Product(name = "긴팔티", price = 20000),
    Product(name = "핸드폰케이스", price = 15000),
    Product(name = "후드티", price = 30000),
    Product(name = "바지", price = 25000)
)

