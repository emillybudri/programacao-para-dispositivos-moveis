// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 07.Faça um programa na Linguagem Kotlin que leia a idade de uma pessoa expressa em ano, mês e dia e mostre-as em dias.

fun main() {
    println("Digite a idade expressa em anos:")
    val anos = readLine().toString().toInt()
    
    println("Digite a idade expressa em meses:")
    val meses = readLine().toString().toInt()
    
    println("Digite a idade expressa em dias:")
    val dias = readLine().toString().toInt()
    
    val totalDias = (anos * 365) + (meses * 30) + dias
    
    println("A idade expressa em dias é: $totalDias dias")
}
