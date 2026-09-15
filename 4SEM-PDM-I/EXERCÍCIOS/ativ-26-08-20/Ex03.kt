// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 03.Faça um programa na Linguagem Kotlin que receba 2 números e apresente a multiplicação dos dois números.

fun main() {
    println("Digite o primeiro número:")
    val num1 = readLine().toString().toDouble()
    
    println("Digite o segundo número:")
    val num2 = readLine().toString().toDouble()
    
    val multiplicacao = num1 * num2
    
    println("O resultado da Multiplicação é $multiplicacao")
}
