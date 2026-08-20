// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 05. Faça um programa na Linguagem Kotlin que leia uma temperatura em graus Fahrenheit e apresentá-la convertida em graus Celsius. A formula de conversão é C <-- (F - 32) * (5/9), sendo f a temperatura em Fahrenheit e C a temperatura em Celsius.

fun main() {
    println("Digite a temperatura em graus Fahrenheit:")
    val fahrenheit = readLine().toString().toDouble()
    
    val celsius = (fahrenheit - 32) * (5.0 / 9.0)
    
    println("A temperatura convertida para Celsius é: $celsius °C")
}
