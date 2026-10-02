package ativ_26_09_10.ex01

// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: Faça um programa na Linguagem Kotlin que leia a idade de uma pessoa e informe sua classificação (0 a 12 anos = criança, 13 a 17 anos = adolescente, 18 a 59 anos = adulto, 60 anos ou mais = idoso).

fun main() {
    println("Digite a idade da pessoa:")
    val idade = readLine().toString().toInt()
    
    if (idade in 0..12) {
        println("Classificação: Criança")
    } else if (idade in 13..17) {
        println("Classificação: Adolescente")
    } else if (idade in 18..59) {
        println("Classificação: Adulto")
    } else if (idade >= 60) {
        println("Classificação: Idoso")
    } else {
        println("Idade inválida.")
    }
}
