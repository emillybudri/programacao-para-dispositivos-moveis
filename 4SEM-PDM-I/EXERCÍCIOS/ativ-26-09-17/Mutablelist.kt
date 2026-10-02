package ativ_26_09_17.mutablelist

// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: Demonstração do uso de MutableList (listas mutáveis) em Kotlin.

fun main() {
    val produtos = mutableListOf(
        "Teclado",
        "Mouse",
        "Monitor"
    )

    produtos.add("Headset")
    produtos.add("Mouse")
    produtos[0] = "Teclado Mecânico"

    println("Produtos: $produtos")
}
