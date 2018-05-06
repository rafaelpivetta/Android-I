package DAO

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import entity.Pedido
import entity.Produto

/**
 * Created by 1731088025 on 10/04/2018.
 */


/**
 * Created by 1731088025 on 10/04/2018.
 */
@Dao
interface PedidoDAO{
    @get:Query("SELECT * FROM pedido")
    val all: List<Pedido>

    @Insert
    fun insert(pedido: Pedido)

    @Delete
    fun delete(pedido: Pedido)
}