// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: Criar um programa em Kotlin que implementa funções com vararg para realizar cálculos de soma, quantidade de elementos e média dos números informados.

/* Versão 1 da Calculadora
fun somar(a: Double, b: Double): Double {
    return a + b
}

fun subtrair(a: Double, b: Double): Double {
    return a - b
}

fun multiplicar (a: Double, b: Double): Double{
    return a * b
}

fun dividir (a: Double, b: Double): Double{
    return a / b
}

fun main() {
    println(somar(10.0, 5.0))
    println(subtrair(10.0, 5.0))
    println(multiplicar(10.0, 5.0))
    println(dividir(10.0, 5.0))
}*/

/* Versão 2 da Calculadora
fun calcular(
    a: Double,
    b: Double,
    operacao: Char): Double {
    return when (operacao) {
        '+' -> a + b
        '-' -> a - b
        '*' -> a * b
        '/' -> a / b
        else -> throw IllegalArgumentException("Operação inválida")
    }
}

fun main() {
    println(calcular(10.0, 5.0, '+'))
    println(calcular(10.0, 5.0, '-'))
    println(calcular(10.0, 5.0, '*'))
    println(calcular(10.0, 5.0, '/'))
}*/

/* Versão 3 da Calculadora
fun calcular(operacao: Char, vararg numeros: Double): Double {
    return when (operacao) {
        '+' -> {
            var resultado = 0.0
            for (numero in numeros) {
                resultado += numero
            }
            resultado
        } else -> 0.0

    }
}

fun main() {
    var resultado = calcular('+', 10.0, 20.0, 30.0, 40.0)
    println(resultado)
}*/

// Versão 4 da Calculadora
data class Resultado(
    val resultado: Double,
    val quantidade: Int,
    val media: Double
)

fun calcular(vararg numeros: Double): Resultado {
    var soma = 0.0

    for (numero in numeros) {
        soma += numero
    }

    val quantidade = numeros.size
    val media = soma / quantidade

    return Resultado(
        soma,
        quantidade, 
        media
    )
}

fun main() {
    val resultado = calcular(10.0, 20.0, 30.0, 40.0)

    println("Soma: ${resultado.resultado}")
    println("Quantidade: ${resultado.quantidade}")
    println("Média: ${resultado.media}")
}
