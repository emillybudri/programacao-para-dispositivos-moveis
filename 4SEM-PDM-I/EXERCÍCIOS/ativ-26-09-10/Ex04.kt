// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: Crie um programa que solicite dois números inteiros: um valor inicial e um valor final. O programa deverá apresentar a soma de todos os números existentes entre os dois valores, incluindo os limites.

fun main() {
    println("Digite o valor inicial:")
    val inicio = readLine().toString().toInt()
    
    println("Digite o valor final:")
    val fim = readLine().toString().toInt()
    
    val min = minOf(inicio, fim)
    val max = maxOf(inicio, fim)
    
    var soma = 0
    for (i in min..max) {
        soma += i
    }
    
    println("A soma dos números entre $min e $max (inclusive) é: $soma")
}
