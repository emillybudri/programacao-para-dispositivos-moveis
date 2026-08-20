// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 12.Faça um programa na Linguagem Kotlin para efetuar a leitura de um número inteiro e apresentar o resultado do quadrado desse número.

fun main() {
    println("Digite um número inteiro:")
    val numero = readLine().toString().toInt()
    
    val quadrado = numero * numero
    
    println("O quadrado de $numero é: $quadrado")
}
