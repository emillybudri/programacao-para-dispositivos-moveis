// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 36. Faça um programa na Linguagem Kotlin que apresente os valores de conversão de graus Celsius em Fahrenheit, de 10 em 10 graus, iniciando a contagem em 10 graus Celsius e finalizando em 100 graus Celsius. O programa deve apresentar os valores das duas temperaturas.

fun main() {
    println("Tabela de Conversão: Celsius -> Fahrenheit")
    println("Celsius (°C)\tFahrenheit (°F)")
    println("------------------------------------")
    
    for (celsius in 10..100 step 10) {
        val fahrenheit = (celsius * 9.0 / 5.0) + 32
        println("$celsius°C\t\t$fahrenheit°F")
    }
}
