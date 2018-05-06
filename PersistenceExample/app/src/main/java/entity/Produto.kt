package entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey



/**
 * Created by 1731088025 on 10/04/2018.
 */
@Entity(tableName = "produto")
data class Produto (
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0,

    @ColumnInfo(name = "nome_produto") var nome: String = "",

    @ColumnInfo(name = "descricao") var descricao: String? = "",

    @ColumnInfo(name = "foto") var foto: String = "",

    @ColumnInfo(name = "valor") var valor: Double = 0.0

)