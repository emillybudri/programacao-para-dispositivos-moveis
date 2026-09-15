// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 31. Faça um programa na Linguagem Kotlin que que receba um número e mostre o fatorial desse número.

fun main() {
    println("Digite um número inteiro para calcular o fatorial:")
    val numero = readLine().toString().toInt()
    
    if (numero < 0) {
        println("Não existe fatorial de número negativo.")
        return
    }
    
    var fatorial: Long = 1
    for (i in 1..numero) {
        fatorial *= i
    }
    
    println("O fatorial de $numero é: $fatorial")
}
