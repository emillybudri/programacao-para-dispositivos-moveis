// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 13.Faça um programa na Linguagem Kotlin que receba um número e mostre o resto da divisão por 6.

fun main() {
    println("Digite um número inteiro:")
    val numero = readLine().toString().toInt()
    
    val resto = numero % 6
    
    println("O resto da divisão de $numero por 6 é: $resto")
}
