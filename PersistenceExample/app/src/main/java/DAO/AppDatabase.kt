package DAO

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import entity.Pedido
import entity.Produto


/**
 * Created by 1731088025 on 10/04/2018.
 */
@Database(entities = arrayOf(Produto::class, Pedido::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDAO
    abstract fun pedidoDao(): PedidoDAO
}