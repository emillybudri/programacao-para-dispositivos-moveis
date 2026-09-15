// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 10.Faça um programa na Linguagem Kotlin que efetue a apresentação do valor da conversão em real de um valor lido em dólar. O programa deve solicitar o valor da cotação do dólar e também a quantidade de dólares disponível com o usuário, para que seja apresentado o valor em moeda brasileira.

fun main() {
    println("Digite a cotação do dólar em reais (ex: 5.25):")
    val cotacao = readLine().toString().toDouble()
    
    println("Digite a quantidade de dólares disponível:")
    val dolares = readLine().toString().toDouble()
    
    val reais = dolares * cotacao
    
    println("O valor equivalente em reais é: R$ $reais")
}
