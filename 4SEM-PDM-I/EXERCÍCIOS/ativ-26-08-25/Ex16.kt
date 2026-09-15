// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 16. Faça um programa na Linguagem Kotlin que leia 3 (três) valores inteiros e apresente os 3 números em ordem crescente.

fun main() {
    println("Digite o primeiro valor inteiro:")
    val num1 = readLine().toString().toInt()
    
    println("Digite o segundo valor inteiro:")
    val num2 = readLine().toString().toInt()
    
    println("Digite o terceiro valor inteiro:")
    val num3 = readLine().toString().toInt()
    
    val numeros = listOf(num1, num2, num3).sorted()
    
    println("Números em ordem crescente: ${numeros.joinToString(", ")}")
}
