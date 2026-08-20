// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 08. Faça um programa na Linguagem Kotlin que calcule a área da circunferência.

fun main() {
    println("Digite o raio da circunferência:")
    val raio = readLine().toString().toDouble()
    
    val area = 3.14159 * raio * raio
    
    println("A área da circunferência é: $area")
}
