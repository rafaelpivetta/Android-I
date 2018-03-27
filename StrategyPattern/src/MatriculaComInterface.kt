// Implementação Strategy Pattern com interface
interface Desconto1 {
    fun calcularDesconto1(): Double
}


class Matricula1(val name: String, val valorCurso: Double, val desconto: (Double) -> Double) : Desconto1 {
    override fun calcularDesconto1(): Double {
        return desconto(valorCurso)
    }

}


fun main(args: Array<String>) {
    val descontoExAluno = { desconto: Double -> desconto * 0.20 }//função é um cidadão de primeira classe
    val descontoAluno = { desconto: Double -> desconto * 0}

    val exAluno = Matricula1("José", 1000.0, desconto = descontoExAluno)//Ser de primeira classe permite que uma função seja passada como parâmetro
    val aluno = Matricula1("João", 1000.0, desconto = descontoAluno)

    println("${exAluno.name} tem desconto de R$ %.2f por mês.".format(exAluno.calcularDesconto1()))
    println("${aluno.name} tem desconto de R$ %.2f por mês.".format(aluno.calcularDesconto1()))
}