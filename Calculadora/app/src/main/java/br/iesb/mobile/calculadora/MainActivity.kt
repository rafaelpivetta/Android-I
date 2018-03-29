package br.iesb.mobile.calculadora

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var listaOperacao: MutableList<String> = mutableListOf()
    var numeroCache: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtZero.setOnClickListener { numeroClick(txtZero.text.toString()) }
        txtUm.setOnClickListener { numeroClick(txtUm.text.toString()) }
        txtDois.setOnClickListener { numeroClick(txtDois.text.toString()) }
        txtTres.setOnClickListener { numeroClick(txtTres.text.toString()) }
        txtQuatro.setOnClickListener { numeroClick(txtQuatro.text.toString()) }
        txtCinco.setOnClickListener { numeroClick(txtCinco.text.toString()) }
        txtSeis.setOnClickListener { numeroClick(txtSeis.text.toString()) }
        txtSete.setOnClickListener { numeroClick(txtSete.text.toString()) }
        txtOito.setOnClickListener { numeroClick(txtOito.text.toString()) }
        txtNove.setOnClickListener { numeroClick(txtNove.text.toString()) }
        txtAC.setOnClickListener { limparClick() }

        txtAdicao.setOnClickListener { operacaoClick(txtAdicao.text.toString()) }
        txtSubtracao.setOnClickListener { operacaoClick(txtSubtracao.text.toString()) }
        txtMultiplicacao.setOnClickListener { operacaoClick(txtMultiplicacao.text.toString()) }
        txtDivisao.setOnClickListener { operacaoClick(txtDivisao.text.toString()) }
        txtPercentual.setOnClickListener { operacaoClick(txtPercentual.text.toString()) }

        txtIgual.setOnClickListener { resultadoClick() }

    }

    private fun limparCache() {
        numeroCache.clear()
        listaOperacao.clear()
    }

    private fun limparClick() {
        numeroCache.clear()
        listaOperacao.clear()
        atualizarResultado("0");
    }

    private fun concatenar(list: List<String>,joiner: String = "") : String {

        if (list.isEmpty()) return ""
        return list.reduce { r, s -> r + joiner + s }
    }

    private fun atualizarResultado(valor: String){
        txtResult.text = valor
    }

    private fun operacaoClick(operacao: String) {

        if (numeroCache.isEmpty()) return

        listaOperacao.add(concatenar(numeroCache))
        numeroCache.clear()
        listaOperacao.add(operacao)

        atualizarResultado(operacao)
    }

    fun resultadoClick() {
        listaOperacao.add(concatenar(numeroCache))
        numeroCache.clear()

        var calculadora = Calculadora()
        var resultado = calculadora.calcular(listaOperacao)
        Log.i("ANTES", resultado.toString()+" "+resultado.toInt().toString())
        var resto = resultado.rem(resultado.toInt())
        Log.i("RESTO", resto.toString())
        if(resto > 0) {
            atualizarResultado(resultado.toString())
        }else{
            atualizarResultado(resultado.toInt().toString())
        }

        limparCache()
    }

    private fun numeroClick(valor: String) {
        numeroCache.add(valor)
        atualizarResultado(concatenar(numeroCache))
    }

}
