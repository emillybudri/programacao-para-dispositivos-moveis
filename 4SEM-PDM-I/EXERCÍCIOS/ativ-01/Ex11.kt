// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 11.Faça um programa na Linguagem Kotlin para ler dois valores inteiros para as variáveis A e B e efetuar a troca dos valores de forma que a variável A passe a possuir o valor da variável B e a variável B passe a possuir o valor da variável A. Apresentar os valores trocados.

fun main() {
    println("Digite o valor para a variável A:")
    var a = readLine().toString().toInt()
    
    println("Digite o valor para a variável B:")
    var b = readLine().toString().toInt()
    
    println("Valores originais: A = $a, B = $b")
    
    // Efetuando a troca dos valores
    val temp = a
    a = b
    b = temp
    
    println("Valores trocados: A = $a, B = $b")
}
