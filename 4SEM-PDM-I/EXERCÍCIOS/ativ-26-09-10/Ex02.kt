package ativ_26_09_10.ex02

// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: Faça um programa na Linguagem Kotlin que solicite 10 números inteiros e, ao final, apresente a quantidade de números positivos, a quantidade de números negativos e a quantidade de zeros.

fun main() {
    var positivos = 0
    var negativos = 0
    var zeros = 0
    
    for (i in 1..10) {
        println("Digite o ${i}º número inteiro:")
        val numero = readLine().toString().toInt()
        
        if (numero > 0) {
            positivos++
        } else if (numero < 0) {
            negativos++
        } else {
            zeros++
        }
    }
    
    println("\nResumo:")
    println("Quantidade de números positivos: $positivos")
    println("Quantidade de números negativos: $negativos")
    println("Quantidade de zeros: $zeros")
}