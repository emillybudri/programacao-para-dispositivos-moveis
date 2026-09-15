// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 24. Faça um programa na Linguagem Kotlin que efetue a leitura de um número inteiro e apresentar uma mensagem informando se o número é par ou ímpar.

fun main() {
    println("Digite um número inteiro:")
    val numero = readLine().toString().toInt()
    
    if (numero % 2 == 0) {
        println("O número $numero é PAR.")
    } else {
        println("O número $numero é ÍMPAR.")
    }
}
