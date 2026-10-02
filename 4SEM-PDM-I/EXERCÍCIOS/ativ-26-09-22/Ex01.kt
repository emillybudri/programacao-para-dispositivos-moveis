package ativ_26_09_22.ex01

// Nome: Emilly Budri Bognar
// Ra: 2171392511009
// Objetivo: Crie um sistema para controlar veículos e vagas de um estacionamento (-veículo: placa, modelo e tipo; -estacionamento: capacidade e veículos estacionados; O sistema deverá permitir registrar entradas e saídas, impedir a entrada quando o estacionamento estiver lotado e informar a quantidade de vagas disponíveis).

class Veiculo(val placa: String, val modelo: String, val tipo: String)

class Estacionamento(val capacidade: Int) {
    private val veiculosEstacionados = mutableListOf<Veiculo>()

    fun registrarEntrada(veiculo: Veiculo): Boolean {
        return if (veiculosEstacionados.size < capacidade) {
            veiculosEstacionados.add(veiculo)
            println("Entrada registrada! Placa: ${veiculo.placa} | Modelo: ${veiculo.modelo} | Tipo: ${veiculo.tipo}")
            true
        } else {
            println("Estacionamento lotado! Não é possível registrar a entrada do veículo ${veiculo.placa}.")
            false
        }
    }

    fun registrarSaida(placa: String): Boolean {
        val veiculo = veiculosEstacionados.find { it.placa == placa }

        return if (veiculo != null) {
            veiculosEstacionados.remove(veiculo)
            println("Saída registrada! Veículo $placa saiu do estacionamento.")
            true
        } else {
            println("Veículo com placa $placa não encontrado no estacionamento.")
            false
        }
    }

    fun vagasDisponiveis(): Int {
        return capacidade - veiculosEstacionados.size
    }
}

fun main() {
    print("Digite a capacidade do estacionamento: ")
    val capacidade = readln().toInt()
    val estacionamento = Estacionamento(capacidade)
    var opcao: Int

    do {
        println("\n--- ESTACIONAMENTO ---")
        println("1 - Registrar entrada")
        println("2 - Registrar saída")
        println("3 - Ver vagas disponíveis")
        println("0 - Sair")
        print("Escolha uma opção: ")
        opcao = readln().toInt()

        when (opcao) {
            1 -> {
                print("Digite a placa: ")
                val placa = readln()
                print("Digite o modelo: ")
                val modelo = readln()
                print("Digite o tipo do veículo: ")
                val tipo = readln()

                val veiculo = Veiculo(placa, modelo, tipo)
                estacionamento.registrarEntrada(veiculo)
            }

            2 -> {
                print("Digite a placa do veículo: ")
                val placa = readln()
                estacionamento.registrarSaida(placa)
            }

            3 -> {
                println("Vagas disponíveis: ${estacionamento.vagasDisponiveis()}")
            }

            0 -> {
                println("Sistema encerrado.")
            }

            else -> {
                println("Opção inválida! Tente novamente.")
            }
        }

    } while (opcao != 0)
}
