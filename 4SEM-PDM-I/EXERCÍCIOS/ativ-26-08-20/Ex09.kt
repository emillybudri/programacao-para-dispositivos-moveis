// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 09.Faça um programa na Linguagem Kotlin para efetuar o cálculo e a apresentação do valor de uma prestação em atraso, utilizando a fórmula PRESTAÇÃO <-- VALOR + (VALOR * (TAXA/100) * TEMPO).

fun main() {
    println("Digite o valor original da prestação:")
    val valor = readLine().toString().toDouble()
    
    println("Digite a taxa de juros (porcentagem mensal, ex: 5 para 5%):")
    val taxa = readLine().toString().toDouble()
    
    println("Digite o tempo de atraso (em meses):")
    val tempo = readLine().toString().toDouble()
    
    val prestacao = valor + (valor * (taxa / 100) * tempo)
    
    println("O valor da prestação em atraso é: R$ $prestacao")
}
