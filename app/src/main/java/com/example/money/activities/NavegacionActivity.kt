package com.example.money.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import com.example.money.R
import com.example.money.activitisopen.SesionActivity
import com.example.money.adapter.PageAdapter
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.defaultSharedPreferences
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

class NavegacionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navegacion)
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        val vPager: ViewPager = findViewById(R.id.viewPager)
        val pAdapter = PageAdapter(supportFragmentManager, tabLayout.tabCount)
        vPager.adapter = pAdapter

        vPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {
                //Toast.makeText(this@NavegacionActivity, "tabeselected: " + tab.text, Toast.LENGTH_LONG).show()
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
                //Toast.makeText(this@NavegacionActivity, "tabUnselected: " + tab.text, Toast.LENGTH_LONG).show()
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                val posicion = tab.position
                vPager.currentItem = posicion
               // Toast.makeText(this@NavegacionActivity, "tabselected: " + tab.text, Toast.LENGTH_LONG).show()
            }
        })

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item!!.itemId) {
        R.id.menu_salir -> {
            startActivity(intentFor<SesionActivity>().newTask().clearTask())
            true
        }
        R.id.menu_olvidar -> {
            //NORMAL.preferencias!!.edit().clear().apply()
            defaultSharedPreferences.edit().clear().apply()
            startActivity(intentFor<SesionActivity>().newTask().clearTask())
            true
        }
        else -> false
    }
}
