package ativ_26_09_17.ex01

// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: O professor precisa de um programa para organizar as notas dos alunos (cadastrar, consultar, alterar, listar alunos/notas, quantidade de alunos, média da turma, maior e menor nota, alunos com nota >= 7.0 e notas distintas).

val notasAlunos = mutableMapOf<String, Double>()

fun cadastrarNota(codigo: String, nota: Double) {
    notasAlunos[codigo] = nota
    println("Aluno cadastrado! Código: $codigo | Nota: $nota")
}

fun consultarNota(codigo: String) {
    val nota = notasAlunos[codigo]
    if (nota != null) {
        println("Código: $codigo | Nota: $nota")
    } else {
        println("Aluno com código $codigo não encontrado.")
    }
}

fun alterarNota(codigo: String, novaNota: Double) {
    if (notasAlunos.containsKey(codigo)) {
        notasAlunos[codigo] = novaNota
        println("Nota alterada! Código: $codigo | Nova nota: $novaNota")
    } else {
        println("Aluno com código $codigo não encontrado.")
    }
}

fun apresentarNotas() {
    if (notasAlunos.isEmpty()) {
        println("Nenhum aluno cadastrado.")
        return
    }

    println("--- ALUNOS E NOTAS ---")
    for ((codigo, nota) in notasAlunos) {
        println("Código: $codigo | Nota: $nota")
    }
}

fun quantidadeAlunos() {
    println("Quantidade de alunos cadastrados: ${notasAlunos.size}")
}

fun calcularMedia() {
    if (notasAlunos.isEmpty()) {
        println("Nenhum aluno cadastrado.")
        return
    }

    val media = notasAlunos.values.average()
    println("Média da turma: $media")
}

fun maiorEMenorNota() {
    if (notasAlunos.isEmpty()) {
        println("Nenhum aluno cadastrado.")
        return
    }

    val maior = notasAlunos.values.maxOrNull() ?: 0.0
    val menor = notasAlunos.values.minOrNull() ?: 0.0

    println("Maior nota: $maior")
    println("Menor nota: $menor")
}

fun alunosComNotaSuperiorA7() {
    val aprovados = notasAlunos.filter { it.value >= 7.0 }

    if (aprovados.isEmpty()) {
        println("Nenhum aluno com nota igual ou superior a 7.0.")
        return
    }

    println("--- ALUNOS COM NOTA >= 7.0 ---")
    for ((codigo, nota) in aprovados) {
        println("Código: $codigo | Nota: $nota")
    }
}

fun diferentesNotas() {
    val notas = notasAlunos.values.toSet()
    println("Notas distintas utilizadas na turma: $notas")
}

fun main() {
    var opcao: Int

    do {
        println("\n--- MENU ---")
        println("1 - Cadastrar aluno")
        println("2 - Consultar nota")
        println("3 - Alterar nota")
        println("4 - Listar todos os alunos")
        println("5 - Quantidade de alunos")
        println("6 - Média da turma")
        println("7 - Maior e menor nota")
        println("8 - Alunos com nota >= 7.0")
        println("9 - Notas distintas")
        println("0 - Sair")

        print("Escolha uma opção: ")
        opcao = readln().toInt()

        when (opcao) {
            1 -> {
                print("Digite o código do aluno: ")
                val codigo = readln()
                print("Digite a nota do aluno: ")
                val nota = readln().toDouble()
                cadastrarNota(codigo, nota)
            }

            2 -> {
                print("Digite o código do aluno: ")
                val codigo = readln()
                consultarNota(codigo)
            }

            3 -> {
                print("Digite o código do aluno: ")
                val codigo = readln()
                print("Digite a nova nota: ")
                val novaNota = readln().toDouble()
                alterarNota(codigo, novaNota)
            }

            4 -> apresentarNotas()
            5 -> quantidadeAlunos()
            6 -> calcularMedia()
            7 -> maiorEMenorNota()
            8 -> alunosComNotaSuperiorA7()
            9 -> diferentesNotas()
            0 -> println("Programa encerrado.")
            else -> println("Opção inválida!")
        }

    } while (opcao != 0)
}
