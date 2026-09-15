// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 06.Faça um programa na Linguagem Kotlin para calcular e apresentar o valor do volume de uma lata de óleo, utilizando a fórmula: VOLUME <-- 3.14159 * RAIO2 * ALTURA.

fun main() {
    println("Digite o raio da lata de óleo:")
    val raio = readLine().toString().toDouble()
    
    println("Digite a altura da lata de óleo:")
    val altura = readLine().toString().toDouble()
    
    val volume = 3.14159 * raio * raio * altura
    
    println("O volume da lata de óleo é: $volume")
}
