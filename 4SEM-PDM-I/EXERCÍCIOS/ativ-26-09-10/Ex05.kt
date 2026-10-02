package ativ_26_09_10.ex05

// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: Crie um programa que leia um número inteiro e informe se ele é ou não um número primo.

fun main() {
    println("Digite um número inteiro:")
    val numero = readLine().toString().toInt()
    
    if (numero <= 1) {
        println("O número $numero NÃO é primo.")
    } else {
        var ehPrimo = true
        for (i in 2..Math.sqrt(numero.toDouble()).toInt()) {
            if (numero % i == 0) {
                ehPrimo = false
                break
            }
        }
        
        if (ehPrimo) {
            println("O número $numero É um número primo.")
        } else {
            println("O número $numero NÃO é um número primo.")
        }
    }
}
