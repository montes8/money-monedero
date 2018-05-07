package com.example.money.activitisopen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.money.R
import com.example.money.activities.NavegacionActivity
import com.example.money.database.DBManagerMoney
import kotlinx.android.synthetic.main.activity_sesion.*
import org.jetbrains.anko.*

class SesionActivity : AppCompatActivity() {

    lateinit var handler:DBManagerMoney

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sesion)
        //FLECHA PARA REGRESAR AL ACTIVITY ANTERIOR
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        ponerPrefSiExisten()
        handler= DBManagerMoney(this)
        button_ingresar.setOnClickListener{
            if (handler.userLogin(edit_usuario.text.toString(),edit_pasword.text.toString())) {
                longToast("el usuario es corecto")
                //startActivity<NavegacionActivity>()
             startActivity(intentFor<NavegacionActivity>().newTask().clearTask())
             guardarPreferencias(edit_usuario.text.toString(), edit_pasword.text.toString())
            }else{
                longToast("el usuario o contraseña incorrectos corecto")
            }
        }
    }

    //guardamos las preferencias del usurio
    fun guardarPreferencias(email: String, pass: String) {
        if (switch_recordar.isChecked) {
            //nos sirve para escribir
            defaultSharedPreferences.edit()
                    .putString("email", email)
                    .putString("pass", pass)
                    .apply()
        }
    }

    //optine las preferencias
    fun ponerPrefSiExisten() {
        val email = defaultSharedPreferences.getString("email", "")
        val pass = defaultSharedPreferences.getString("pass", "")
        if (!email.isNullOrEmpty() && !pass.isNullOrEmpty()) {
            edit_usuario.setText(email)
            edit_pasword.setText(pass)
            switch_recordar.isChecked = false
        }
    }
}
