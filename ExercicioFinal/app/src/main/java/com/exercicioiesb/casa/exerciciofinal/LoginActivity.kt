package com.exercicioiesb.casa.exerciciofinal

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import com.exercicioiesb.casa.exerciciofinal.dao.AppDatabase
import com.exercicioiesb.casa.exerciciofinal.entity.Usuario
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        tvCriarConta.setOnClickListener {
            startActivity(Intent(this@LoginActivity, NovoUsuarioActivity::class.java))
        }

        tvRedefinirSenha.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RedefinirSenhaActivity::class.java))
        }

        btnLogin.setOnClickListener {

            var u : Usuario = Usuario()
            u.email = edtEmail.text.toString()
            u.senha = edtSenha.text.toString()

            if(u.email.isEmpty() || u.senha.isEmpty()){
                Toast.makeText(this, "Preencha o campo Email e/ou Senha", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            var util : Util = Util()
            if(!util.emailValido(u.email)){
                Toast.makeText(this, "Email inválido", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            var db = Room.databaseBuilder(applicationContext,
                    AppDatabase::class.java, "exerciciofinal").allowMainThreadQueries().build()


            var usuario: Usuario = db.usuarioDao().findUserByEmailAndPass(u.email, u.senha)
            if(usuario != null) {//Rever, pois sempre retorna true?

                Log.i("Loginusuario: sucesso->", usuario.matricula)
                val it = Intent(this@LoginActivity, MainActivity::class.java)
                it.putExtra("email", u.email)
                it.putExtra("senha", u.senha)
                db.close()
                startActivity(it)
                finish()
            }else{
                Toast.makeText(this, "Verifique o email ou a senha", Toast.LENGTH_LONG).show()
                edtEmail.text = Editable.Factory.getInstance().newEditable("")
            }
        }
    }
}
