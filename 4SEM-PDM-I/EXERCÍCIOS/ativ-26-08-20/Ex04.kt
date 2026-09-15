// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 04.Faça um programa na Linguagem Kotlin que leia uma temperatura em graus Celsius e apresentá-la convertida em graus Fahrenheit. A fórmula de conversão é F <-- (9 * C + 160) /5, sendo f a temperatura em Fahrenheit e C a temperatura em Celsius.

fun main() {
    println("Digite a temperatura em graus Celsius:")
    val celsius = readLine().toString().toDouble()
    
    val fahrenheit = (9 * celsius + 160) / 5
    
    println("A temperatura convertida para Fahrenheit é: $fahrenheit °F")
}
