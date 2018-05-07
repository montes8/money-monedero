package com.example.money.activitisopen

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.money.R
import com.example.money.activities.NavegacionActivity
import com.example.money.database.DBManagerMoney
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

class RegisterActivity : AppCompatActivity() {
    var id=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //FLECHA PARA REGRESAR AL ACTIVITY ANTERIOR
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        try {
            val bundle: Bundle = intent.extras
            id = bundle.getInt("ID", 0)
            if (id !=0) {
                edit_text_name.setText(bundle.getString("name"))
                edit_text_usuario.setText(bundle.getString("user"))
                edit_text_password.setText(bundle.getString("pass"))
            }
        }catch (e:Exception){

        }
    }

    fun btnRegisterUser( view : View){

        val baseDatosUser= DBManagerMoney(this)
        val values = ContentValues()
        values.put("Nombre",edit_text_name.text.toString())
        values.put("Usuario",edit_text_usuario.text.toString())
        values.put("password",edit_text_password.text.toString())

        if (id==0) {
            val ID = baseDatosUser.insertarUser(values)
            if (ID > 0) {
                Toast.makeText(this, "Base de Datos Creada", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Usuario Registrado Correctamente", Toast.LENGTH_LONG).show()
                startActivity(intentFor<NavegacionActivity>().newTask().clearTask())
                finish()
            } else {
                Toast.makeText(this, "Lo Siento Usuario no Agregado,por favor intentalo de nuevo", Toast.LENGTH_LONG).show()
            }
        }else{
            val selectionArgs = arrayOf(id.toString())
            val ID = baseDatosUser.actualizarUser(values,"ID=?",selectionArgs)

            if (ID > 0) {
                Toast.makeText(this, "Usuario Agregado Correctamente", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Lo Siento Usuario no Agregado,por favor intentalo de nuevo", Toast.LENGTH_LONG).show()
            }
        }
    }

}
