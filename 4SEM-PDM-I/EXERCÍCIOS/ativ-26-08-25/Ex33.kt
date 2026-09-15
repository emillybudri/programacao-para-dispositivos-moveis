// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 33. Faça um programa na Linguagem Kotlin para apresentar o total da soma obtida dos cem primeiros números inteiros ( 1 + 2 + 3 + 4 + .... 98+ 99+ 100).

fun main() {
    var soma = 0
    for (i in 1..100) {
        soma += i
    }
    
    println("O total da soma dos cem primeiros números inteiros é: $soma")
}
