package com.example.money.listas


import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.money.R
import com.example.money.activitisopen.RegisterActivity
import com.example.money.clases.User
import com.example.money.database.DBManagerMoney
import kotlinx.android.synthetic.main.activity_lista_usuario.*
import kotlinx.android.synthetic.main.molde_list_user.view.*


class ListaUsuario (var adapter: UsuarioAdapter? = null): AppCompatActivity() {

    var listaUsuarios=ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_usuario)

        cargarQueryUser("%")
    }
    override fun onResume() {
        super.onResume()
        cargarQueryUser("%")
    }
    fun cargarQueryUser(usuario: String){

        var baseDatos= DBManagerMoney(this)//
        val columnas = arrayOf("ID","Nombre","Usuario","password")
        val selectionArgs = arrayOf(usuario)
        val cursor = baseDatos.queryUser(columnas,"Nombre like ?",selectionArgs,"Nombre")
        listaUsuarios.clear()
        if(cursor.moveToFirst()){
            do {
                val ID=cursor.getInt(cursor.getColumnIndex("ID"))
                val nombre =cursor.getString(cursor.getColumnIndex("Nombre"))
                val usuario =cursor.getString(cursor.getColumnIndex("Usuario"))
                val password =cursor.getString(cursor.getColumnIndex("password"))
                listaUsuarios.add(User(ID,nombre,usuario,password))
            }while (cursor.moveToNext())
        }
        adapter = UsuarioAdapter(this, listaUsuarios)
        list_view_user.adapter = adapter

    }

    inner class UsuarioAdapter(contexto: Context, var listaUsuarios:ArrayList<User>): BaseAdapter(){

        var contexto: Context?=contexto

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var convertView : View?=convertView
            if (convertView==null){
                convertView= View.inflate(contexto,R.layout.molde_list_user,null)
            }
            val usuario =listaUsuarios[position]
            val miVista =convertView!!
            miVista.text_view_nombre1.text=usuario.usuName
            miVista.text_view_usuario1.text=usuario.usuario
            miVista.text_view_password1.text=usuario.password

            miVista.image_delete_user!!.setOnClickListener{
                val dbManagerUser= DBManagerMoney(this.contexto!!)//
                val selectionArgs =arrayOf(usuario.usuId.toString())
                dbManagerUser.borrarUser("ID=?",selectionArgs)
                cargarQueryUser("%")
            }

            miVista.image_edit_user!!.setOnClickListener{
                val intent= Intent(this@ListaUsuario, RegisterActivity::class.java)
                intent.putExtra("ID",usuario.usuId)
                intent.putExtra("Nombre",usuario.usuName)
                intent.putExtra("Usuario",usuario.usuario)
                intent.putExtra("password",usuario.password)
                startActivity(intent)
            }

            return miVista
        }

        override fun getItem(position: Int): Any {
            return listaUsuarios[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listaUsuarios.size
        }
    }

}
