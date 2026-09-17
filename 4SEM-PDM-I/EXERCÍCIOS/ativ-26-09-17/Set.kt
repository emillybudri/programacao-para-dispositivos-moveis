// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: Demonstração do uso de Set e MutableSet (conjuntos sem duplicatas) em Kotlin.

fun main() {
    val categorias = mutableSetOf(
        "Informatica",
        "Eletronicos",
        "Informatica",
        "Acessorios"
    )

    categorias.add("Celulares")
    categorias.add("Informatica")

    println("Categorias: $categorias")
    println("Quantidade de categorias: ${categorias.size}")
}
