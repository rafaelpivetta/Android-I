package br.iesb.mobile.persistenceexample

import DAO.AppDatabase
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import entity.Pedido
import entity.Produto
import kotlinx.android.synthetic.main.activity_main.*

class PedidoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)

        val db = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "loja_produtos").allowMainThreadQueries().build()

        val lista:List<Pedido>? = db.pedidoDao().all
        if(lista != null) {
            for (p in lista) {




                val button_dynamic = Button(this)

                button_dynamic.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                button_dynamic.text = p.nome
                button_dynamic.setTag(p)
                // add Button to LinearLayout
                row.addView(button_dynamic)

                if(p.foto != null && p.foto != ""){
                    
                }

            }
        }
    }
}
