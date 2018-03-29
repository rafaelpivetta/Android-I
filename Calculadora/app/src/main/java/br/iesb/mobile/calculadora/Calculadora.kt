package br.iesb.mobile.calculadora

import android.util.Log

/**
 * Created by pivetta on 28/03/18.
 */
class Calculadora: ICalculadora {

    override fun calcular(operacaoLista: List<String>): Double {
        var numero: Double = 0.0
        var operacao: String = ""

        for (op in operacaoLista){
            Log.i("OP", op)
            when {
                op.equals("+")
                        || op.equals("/")
                        || op.equals("x")
                        || op.equals("-") -> operacao = op

                operacao.equals("x") -> numero *= op.toDouble()
                operacao.equals("-") -> numero -= op.toDouble()
                operacao.equals("/") -> numero /= op.toDouble()

                else -> numero += op.toDouble()

            }
        }

        return numero
    }
}