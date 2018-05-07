package com.example.money.listas


import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.money.R
import com.example.money.activities.CategoriaActivity
import com.example.money.clases.Categoria
import com.example.money.database.DBManagerMoney
import kotlinx.android.synthetic.main.activity_lista_categoria.*
import kotlinx.android.synthetic.main.molde_categoria.view.*

class ListaCategoria (var adapter: CategoriaAdapter? = null): AppCompatActivity() {

    var listaCategorias=ArrayList<Categoria>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_categoria)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        cargarQuery("%")
    }

    override fun onResume() {
        super.onResume()
        cargarQuery("%")
    }
    fun cargarQuery(categoria: String){

        val baseDatos=DBManagerMoney(this)//
        val columnas = arrayOf("ID","Categoria")
        val selectionArgs = arrayOf(categoria)
        val cursor = baseDatos.queryCategoria(columnas,"Categoria like ?",selectionArgs,"Categoria")
        listaCategorias.clear()
        if(cursor.moveToFirst()){
            do {
                val ID=cursor.getInt(cursor.getColumnIndex("ID"))
                val title =cursor.getString(cursor.getColumnIndex("Categoria"))
                listaCategorias.add(Categoria(ID,title))
            }while (cursor.moveToNext())
        }
        adapter = CategoriaAdapter(this, listaCategorias)
        list_view.adapter = adapter
    }

    inner class CategoriaAdapter(contexto:Context,var listaCategorias:ArrayList<Categoria>):BaseAdapter(){

        var contexto:Context?=contexto

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var convertView : View ?=convertView
            if (convertView==null){
                convertView=View.inflate(contexto,R.layout.molde_categoria,null)
            }
            val categoria =listaCategorias[position]
            val miVista =convertView!!
            miVista.text_view_categoria.text=categoria.tituloCategoria
            miVista.image_delete_categoria!!.setOnClickListener{
                val dbManagerCat= DBManagerMoney(this.contexto!!)//
                val selectionArgs =arrayOf(categoria.categoriaId.toString())
                dbManagerCat.borrarCategoria("ID=?",selectionArgs)
                cargarQuery("%")
            }
            miVista.image_edit_categoria!!.setOnClickListener{
                val intent=Intent(this@ListaCategoria,CategoriaActivity::class.java)
                intent.putExtra("ID",categoria.categoriaId)
                intent.putExtra("tituloCategoria",categoria.tituloCategoria)
                startActivity(intent)
            }
            return miVista
        }

        override fun getItem(position: Int): Any {
            return listaCategorias[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
           return listaCategorias.size
        }
    }
}
