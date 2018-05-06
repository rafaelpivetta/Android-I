package DAO

import android.arch.persistence.room.*
import entity.Produto


/**
 * Created by 1731088025 on 10/04/2018.
 */
@Dao
interface ProdutoDAO {
    @get:Query("SELECT * FROM produto")
    val all: List<Produto>

    @Query("SELECT * FROM produto WHERE uid IN (:uid)")
    fun loadAllById(uid: Int): Produto

    @Insert
    fun insert(produto: Produto)

    @Delete
    fun delete(produto: Produto)
}