package com.exercicioiesb.casa.exerciciofinal

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.exercicioiesb.casa.exerciciofinal.dao.AppDatabase
import com.exercicioiesb.casa.exerciciofinal.entity.Usuario
import kotlinx.android.synthetic.main.activity_perfilusuario.*
import android.content.Intent
import android.text.Editable
import android.widget.Toast


class PerfilUsuarioActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfilusuario)

        var db = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "exerciciofinal").allowMainThreadQueries().build()


        val it = intent

        var email = it.getStringExtra("email")

        Log.i("Perfilusuario", "Email passado como parâmetro: "+email)
        var usuario: Usuario = db.usuarioDao().findUserByEmail(email)


        if(usuario != null) {

            Log.i("Perfilusuario", "encontrado? "+usuario.email+" "+usuario.senha+" "+usuario.uid.toString())
            if(!usuario.nome.isNullOrBlank())
                edtNomeUsuario.text = Editable.Factory.getInstance().newEditable(usuario.nome)

            if(!usuario.matricula.isNullOrBlank())
                edtMatricula.text = Editable.Factory.getInstance().newEditable(usuario.matricula)

            if(!usuario.telefone.isNullOrBlank())
                edtTelefone.text = Editable.Factory.getInstance().newEditable(usuario.telefone)

        }else{
            Log.i("Perfilusuario", "não encontrado")
        }
        //Não faz sentido preencher os campos senha e confirma senha nesse momento.

        btnSalvar.setOnClickListener{

            val util : Util = Util()

            if(edtNomeUsuario.text.isEmpty() || edtMatricula.text.isEmpty() || edtTelefone.text.isEmpty() || edtSenha.text.isEmpty() || edtConfirmarSenha.text.isEmpty()){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if(!util.comparaSenhas(edtSenha.text.toString(), edtConfirmarSenha.text.toString())){
                Toast.makeText(this, "Senhas são diferentes", Toast.LENGTH_LONG).show()
                edtSenha.setText("")
                edtConfirmarSenha.setText("")
                return@setOnClickListener
            }

            if(!util.senhaValida(edtSenha.text.toString())){
                Toast.makeText(this, "A senha deve conter 6 dígitos, sendo pelo menos um caractere maiúsculo, um caractere especial e um número", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            var u : Usuario = Usuario()
            u.uid = usuario.uid
            u.email = email
            u.nome = edtNomeUsuario.text.toString()
            u.matricula = edtMatricula.text.toString()
            u.telefone = edtTelefone.text.toString()
            u.senha = edtSenha.text.toString()

            Log.i("Perfilusuario", "Campos: "+ u.nome+" "+u.email+" "+u.matricula+" "+ u.telefone+" "+u.senha+" "+u.uid)

            db.usuarioDao().update(u)
            Toast.makeText(this, "Perfil atualizado", Toast.LENGTH_LONG).show()
            db.close()
            finish()
        }

    }

}