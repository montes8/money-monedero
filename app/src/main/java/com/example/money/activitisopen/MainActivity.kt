package com.example.money.activitisopen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.money.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_sesion.setOnClickListener{
            startActivity<SesionActivity>()
        }
        button_register.setOnClickListener{
            startActivity<RegisterActivity>()
        }
    }
}
