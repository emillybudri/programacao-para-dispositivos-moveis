package ativ_26_09_10.ex03

// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: Faça um programa na Linguagem Kotlin que leia um número inteiro positivo e faça uma contagem regressiva até zero.

fun main() {
    println("Digite um número inteiro positivo:")
    val numero = readLine().toString().toInt()
    
    if (numero < 0) {
        println("Por favor, digite um número inteiro positivo.")
    } else {
        println("\nContagem regressiva:")
        for (i in numero downTo 0) {
            println(i)
        }
    }
}
