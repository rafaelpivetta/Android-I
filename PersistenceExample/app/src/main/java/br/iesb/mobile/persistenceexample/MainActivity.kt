package br.iesb.mobile.persistenceexample

import DAO.AppDatabase
import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import entity.Pedido
import entity.Produto
import kotlinx.android.synthetic.main.activity_add_produto.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPlus.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddProdutoActivity::class.java))
        }

        buttonCarrinho.setOnClickListener {
            startActivity(Intent(this@MainActivity, PedidoActivity::class.java))
        }

        val db = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "loja_produtos").allowMainThreadQueries().build()

        val lista:List<Produto>? = db.produtoDao().all
        if(lista != null) {
            for (p in lista) {

                val button_dynamic = Button(this)

                button_dynamic.setOnClickListener { button ->
                    var prod : Produto = button.getTag() as Produto

                    var pe : Pedido = Pedido()
                    pe.nome = prod.nome
                    pe.descricao = prod.descricao
                    pe.valor = prod.valor
                    pe.foto = prod.foto

                    db.pedidoDao().insert(pe)
                    startActivity(Intent(this@MainActivity, PedidoActivity::class.java))
                }
                // setting layout_width and layout_height using layout parameters

                button_dynamic.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                button_dynamic.text = p.nome
                button_dynamic.setTag(p)
                // add Button to LinearLayout
                row.addView(button_dynamic)
            }
        }
    }
}
