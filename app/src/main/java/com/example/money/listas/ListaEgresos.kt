package com.example.money.listas

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.money.R
import com.example.money.clases.Egresos
import com.example.money.database.DBManagerMoney
import kotlinx.android.synthetic.main.activity_lista_egresos.*
import kotlinx.android.synthetic.main.molde_lista_gasto.view.*

class ListaEgresos (var adapter:EgresoAdapter? = null) : AppCompatActivity() {
    var listaEgresos=ArrayList<Egresos>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_egresos)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        cargarQueryEgreso("%")
    }
    override fun onResume() {
        super.onResume()
        cargarQueryEgreso("%")
    }
    fun cargarQueryEgreso(egreso: String){

        val baseDatos= DBManagerMoney(this)//
        val columnas = arrayOf("ID","CategoriaEgreso","Egreso","MontoEgreso","FechaEgreso","MesEgreso","AnioEgreso")
        val selectionArgs = arrayOf(egreso)
        val cursor = baseDatos.queryEgreso(columnas,"CategoriaEgreso like ?",selectionArgs,"CategoriaEgreso")
        listaEgresos.clear()
        if(cursor.moveToFirst()){
            do {
                val ID=cursor.getInt(cursor.getColumnIndex("ID"))
                val catEgreso=cursor.getString(cursor.getColumnIndex("CategoriaEgreso"))
                val titulo =cursor.getString(cursor.getColumnIndex("Egreso"))
                val monto =cursor.getString(cursor.getColumnIndex("MontoEgreso"))
                val fecha =cursor.getString(cursor.getColumnIndex("FechaEgreso"))
                val meses =cursor.getString(cursor.getColumnIndex("MesEgreso"))
                val anioss =cursor.getString(cursor.getColumnIndex("AnioEgreso"))
                listaEgresos.add(Egresos(ID,catEgreso,titulo,monto,fecha,meses,anioss))
            }while (cursor.moveToNext())
        }
        adapter = EgresoAdapter(this,listaEgresos)
        list_view_egresos.adapter = adapter

    }






    inner class EgresoAdapter(contexto: Context, var listaEgresos:ArrayList<Egresos>): BaseAdapter(){
        var contexto: Context?=contexto

        @SuppressLint("SetTextI18n")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var convertView : View?=convertView
            if (convertView==null){
                convertView= View.inflate(contexto,R.layout.molde_lista_gasto,null)
            }
            val egreso =listaEgresos[position]
            val miVista =convertView!!
            miVista.text_categoria_egreso.text=egreso.categoriaEgreso
            miVista.text_egreso_descricion.text=egreso.descripcionEngreso
            miVista.text_monto_engreso.text=egreso.montoEngreso
            miVista.text_fecha_gasto.text=egreso.fechaEngreso+"/"+egreso.mesEgreso+"/"+egreso.anioEgreso
            miVista.image_delete_egreso!!.setOnClickListener{
                val dbEgreso= DBManagerMoney(this.contexto!!)//
                val selectionArgs =arrayOf(egreso.EngresoId.toString())

                dbEgreso.borrarEgreso("ID=?",selectionArgs)
                cargarQueryEgreso("%")
            }
            return miVista
        }

        override fun getItem(position: Int): Any {
            return listaEgresos[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listaEgresos.size
        }
    }


}
