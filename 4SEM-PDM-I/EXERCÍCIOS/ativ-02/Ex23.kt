// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 23. Faça um programa na Linguagem Kotlin que efetue a leitura de cinco números inteiros e identificar o maior e o menor valor. Não execute a ordenação de valores.

fun main() {
    println("Digite o 1º número inteiro:")
    var num = readLine().toString().toInt()
    
    var maior = num
    var menor = num
    
    for (i in 2..5) {
        println("Digite o $iº número inteiro:")
        num = readLine().toString().toInt()
        
        if (num > maior) {
            maior = num
        }
        if (num < menor) {
            menor = num
        }
    }
    
    println("O maior valor é: $maior")
    println("O menor valor é: $menor")
}
