// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 22. Faça um programa na Linguagem Kotlin que leia 3 números inteiros e mostre o maior deles.

fun main() {
    println("Digite o primeiro número inteiro:")
    val num1 = readLine().toString().toInt()
    
    println("Digite o segundo número inteiro:")
    val num2 = readLine().toString().toInt()
    
    println("Digite o terceiro número inteiro:")
    val num3 = readLine().toString().toInt()
    
    val maior = if (num1 >= num2 && num1 >= num3) {
        num1
    } else if (num2 >= num1 && num2 >= num3) {
        num2
    } else {
        num3
    }
    
    println("O maior número é: $maior")
}
