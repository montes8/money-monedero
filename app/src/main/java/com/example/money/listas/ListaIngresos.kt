package com.example.money.listas

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.money.R
import com.example.money.clases.Ingresos
import com.example.money.database.DBManagerMoney
import kotlinx.android.synthetic.main.activity_lista_ingresos.*
import kotlinx.android.synthetic.main.model_lista.view.*


class ListaIngresos(var adapter: IngresoAdapter? = null) : AppCompatActivity() {

    var listaIngresos = ArrayList<Ingresos>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_ingresos)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        cargarQueryIngreso("%")
    }

    override fun onResume() {
        super.onResume()
        cargarQueryIngreso("%")
    }

    fun cargarQueryIngreso(ingreso: String) {

        var baseDatos = DBManagerMoney(this)//
        val columnas = arrayOf("ID", "Ingreso", "MontoIngreso", "FechaIngreso","MesIngreso","AnioIngreso")
        val selectionArgs = arrayOf(ingreso)
        val cursor = baseDatos.queryIngreso(columnas, "Ingreso like ?", selectionArgs, "Ingreso")
        listaIngresos.clear()
        if (cursor.moveToFirst()) {
            do {
                val ID = cursor.getInt(cursor.getColumnIndex("ID"))
                val ingreso = cursor.getString(cursor.getColumnIndex("Ingreso"))
                val montos = cursor.getString(cursor.getColumnIndex("MontoIngreso"))
                val fechas = cursor.getString(cursor.getColumnIndex("FechaIngreso"))
                val mese = cursor.getString(cursor.getColumnIndex("MesIngreso"))
                val anio = cursor.getString(cursor.getColumnIndex("AnioIngreso"))
                listaIngresos.add(Ingresos(ID, ingreso, montos, fechas,mese,anio))
            } while (cursor.moveToNext())
        }
        adapter = IngresoAdapter(this, listaIngresos)
        list_view_ingresos.adapter = adapter

      }

    inner class IngresoAdapter(contexto: Context, var listaIngresos: ArrayList<Ingresos>) : BaseAdapter() {

        var contexto: Context? = contexto

        @SuppressLint("SetTextI18n")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var convertView: View? = convertView
            if (convertView == null) {
                convertView = View.inflate(contexto, R.layout.model_lista, null)
            }
            val ingreso = listaIngresos[position]
            val miVista = convertView!!
            //miVista.text_categoria.text=ingreso.usuIngresoId.toString()
            miVista.text_ingreso.text = ingreso.usuIngreso
            miVista.text_monto_ingreso.text = ingreso.montoIngreso
            miVista.text_fecha.text = ingreso.fechaIngreso+"/"+ingreso.mesIngreso+"/"+ingreso.anioIngreso
            miVista.image_delete!!.setOnClickListener {
                val dbIngreso = DBManagerMoney(this.contexto!!)//
                val selectionArgs = arrayOf(ingreso.usuIngresoId.toString())
                dbIngreso.borrarIngreso("ID=?", selectionArgs)
                cargarQueryIngreso("%")
            }

            return miVista
        }

        override fun getItem(position: Int): Any {
            return listaIngresos[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listaIngresos.size
        }
    }

}
