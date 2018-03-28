package br.iesb.mobile.calculadora

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        updateDisplay("")
    }

    val listaOperacao: MutableList<String> = arrayListOf()
    val numeroCache: MutableList<String> = arrayListOf()

    fun limpar() {
        numeroCache.clear()
        listaOperacao.clear()
    }

    fun clickNumero(numero: String) {


        numeroCache.add(numero)

    }


}
