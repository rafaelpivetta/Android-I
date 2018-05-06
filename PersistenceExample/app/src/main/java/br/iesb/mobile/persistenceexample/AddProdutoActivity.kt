package br.iesb.mobile.persistenceexample


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import entity.Produto
import kotlinx.android.synthetic.main.activity_add_produto.*
import DAO.AppDatabase
import android.Manifest
import android.app.Activity
import android.arch.persistence.room.Room
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import java.io.ByteArrayOutputStream
import java.util.*
import android.util.Base64


class AddProdutoActivity : AppCompatActivity() {

    private val CAMERA = 1
    private var imagemTexto : String = ""

    companion object{
        const val REQUEST_PERMISSION = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_produto)
        imagem.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_PERMISSION)
            }else {
                takePhotoFromCamera()
            }
        }

        buttonSalvar.setOnClickListener {

            var p : Produto = Produto()
            p.nome = editTextNome.text.toString()
            p.descricao = editTextDescricao.text.toString()
            p.valor = editTextValor.text.toString().toDouble()
            p.foto = imagemTexto

            val db = Room.databaseBuilder(applicationContext,
                    AppDatabase::class.java, "loja_produtos").allowMainThreadQueries().build()

            db.produtoDao().insert(p)

            startActivity(Intent(this@AddProdutoActivity, MainActivity::class.java))
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode){
            REQUEST_PERMISSION -> {
                if( permissions.contains(Manifest.permission.CAMERA) && grantResults.contains(PackageManager.PERMISSION_GRANTED) ){
                    takePhotoFromCamera()
                }
            }
        }
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        val thumbnail = data!!.extras!!.get("data") as Bitmap
        imagem.setImageBitmap(thumbnail)

        val outByte = ByteArrayOutputStream()

        thumbnail.compress(Bitmap.CompressFormat.PNG, 100, outByte)

        imagemTexto = Base64.encodeToString(outByte.toByteArray(), Base64.DEFAULT)

        //  Toast.makeText(this@MainActivity, "Foto capturada!", Toast.LENGTH_SHORT).show()

        //Toast.makeText(this@MainActivity, "Base64: " + base64, Toast.LENGTH_SHORT).show()
    }
}
