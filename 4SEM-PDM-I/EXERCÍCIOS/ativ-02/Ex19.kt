// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: 19. Faça um programa na Linguagem Kotlin que leia quatros valores referentes a quatro notas escolares de um aluno e imprimir uma mensagem dizendo que o aluno foi aprovado, se o valor da média escolar for maior ou igual a 7. Se o aluno não foi aprovado, indicar uma mensagem informando esta condição. Apresentar junto das mensagens o valor da média do aluno para qualquer condição.

fun main() {
    println("Digite a primeira nota:")
    val nota1 = readLine().toString().toDouble()
    
    println("Digite a segunda nota:")
    val nota2 = readLine().toString().toDouble()
    
    println("Digite a terceira nota:")
    val nota3 = readLine().toString().toDouble()
    
    println("Digite a quarta nota:")
    val nota4 = readLine().toString().toDouble()
    
    val media = (nota1 + nota2 + nota3 + nota4) / 4.0
    
    if (media >= 7.0) {
        println("Aluno Aprovado! Média escolar: $media")
    } else {
        println("Aluno Reprovado! Média escolar: $media")
    }
}
