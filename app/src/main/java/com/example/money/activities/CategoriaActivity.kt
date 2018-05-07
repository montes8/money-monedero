package com.example.money.activities

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.money.R
import com.example.money.database.DBManagerMoney
import com.example.money.listas.ListaCategoria
import kotlinx.android.synthetic.main.activity_categoria.*
import org.jetbrains.anko.startActivity

class CategoriaActivity : AppCompatActivity() {
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria)

        try {
            val bundle: Bundle = intent.extras
            id = bundle.getInt("ID", 0)
            if (id != 0) {
                edit_text_categoria.setText(bundle.getString("categoria"))
            }
          } catch (e: Exception) {
        }
    }

    fun btnAgregarCategoria(view: View) {
        val baseDatos = DBManagerMoney(this)
        val values = ContentValues()
        values.put("Categoria", edit_text_categoria.text.toString())
        if (id == 0) {
              val ID = baseDatos.insertarCategoria(values)
              if (ID > 0) {
                   Toast.makeText(this, "Categoria agregada correctamente", Toast.LENGTH_LONG).show()
                   startActivity<ListaCategoria>()
                    finish()
                } else {
                Toast.makeText(this, "Lo siento la categoria no se agrego correctamente ,por favor intentalo de nuevo", Toast.LENGTH_LONG).show()
                }
         } else {
              val selectionArgs = arrayOf(id.toString())
              val ID = baseDatos.actualizarCategoria(values, "ID=?", selectionArgs)
              if (ID > 0) {
                   Toast.makeText(this, "categoria agregada correctamente", Toast.LENGTH_LONG).show()
                   finish()
              } else {
                  Toast.makeText(this, "Lo siento la categoria nose agrego correctamente ,por favor intentalo de nuevo", Toast.LENGTH_LONG).show()
            }
        }
    }
}
