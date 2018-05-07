package com.example.money.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast


class DBManagerMoney{
    val dbNombre="AdministradorDinero"
    val dbVersion=1

    val dbTabla="Categorias"
    val columnaID="ID"
    val columnaCategoria="Categoria"
    //creamos la tabla de categorias
    val sqlCrearTablaCategoria ="CREATE TABLE IF NOT EXISTS " + dbTabla +" ("+ columnaID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            columnaCategoria +" TEXT NOT NULL)"
    /////////////////////////////////////////////////////////////////////
    val dbTablaUser="Usuarios"
    val columnaUserID="ID"
    val columnaNombre="Nombre"
    val columnaUsuario="Usuario"
    val columnaContraseña="password"
    //creamos la tabla de categorias
    val sqlCrearTablaUser ="CREATE TABLE IF NOT EXISTS " + dbTablaUser +" ("+ columnaUserID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            columnaNombre +" TEXT NOT NULL,"+
            columnaUsuario +" TEXT NOT NULL,"+
            columnaContraseña +" TEXT NOT NULL)"
    ////////////////////////////////////////////////////////////////////////
    val dbTablaIngresos="Ingresos"
    val columnaIngresosID="ID"
    val columnaIngreso="Ingreso"
    val columnaIngresoMonto="MontoIngreso"
    val columnaFechaIngreso="FechaIngreso"
    val columnaMesIngreso="MesIngreso"
    val columnaAnioIngreso="AnioIngreso"
    //creamos la tabla ingresos
    val sqlCrearTablaIngresos ="CREATE TABLE IF NOT EXISTS " + dbTablaIngresos +" ("+ columnaIngresosID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            columnaIngreso +" TEXT NOT NULL,"+
            columnaIngresoMonto +" TEXT NOT NULL,"+
            columnaFechaIngreso +" TEXT NOT NULL,"+
            columnaMesIngreso +" TEXT NOT NULL,"+
            columnaAnioIngreso +" TEXT NOT NULL)"
    //////////////////////////////////////////////////////////////////////
    val dbTablaEgresos="Egresos"
    val columnaEgresosID="ID"
    val columnaCategoriaEgreso="CategoriaEgreso"
    val columnaEgreso="Egreso"
    val columnaEgresoMonto="MontoEgreso"
    val columnaFechaEgreso="FechaEgreso"
    val columnaMesEngreso="MesEgreso"
    val columnaAnioEngreso="AnioEgreso"
    //creamos la tabla ingresos
    val sqlCrearTablaEgresos ="CREATE TABLE IF NOT EXISTS " + dbTablaEgresos +" ("+ columnaEgresosID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            columnaCategoriaEgreso +" TEXT NOT NULL,"+
            columnaEgreso +" TEXT NOT NULL,"+
            columnaEgresoMonto +" TEXT NOT NULL,"+
            columnaFechaEgreso +" TEXT NOT NULL,"+
            columnaMesEngreso +" TEXT NOT NULL,"+
            columnaAnioEngreso +" TEXT NOT NULL)"


    var sqlDB:SQLiteDatabase?=null
    constructor(contexto: Context){
        val db=DBHerlperFunciones(contexto)
       sqlDB=db.writableDatabase
    }


    inner class DBHerlperFunciones(contexto: Context):SQLiteOpenHelper(contexto,dbNombre,null,dbVersion){

         var contexto:Context=contexto

        override fun onCreate(db: SQLiteDatabase?) {

            db!!.execSQL(sqlCrearTablaCategoria)
            db.execSQL(sqlCrearTablaUser)
            db.execSQL(sqlCrearTablaIngresos)
            db.execSQL(sqlCrearTablaEgresos)
            Toast.makeText(this.contexto,"la base de datos administrar Dinero a sido creada",Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table IF EXISTS"+dbTabla)
            db.execSQL("Drop table IF EXISTS"+dbTablaUser)
            db.execSQL("Drop table IF EXISTS"+dbTablaIngresos)
            db.execSQL("Drop table IF EXISTS"+dbTablaEgresos)
        }
    }


    fun insertarCategoria(values:ContentValues):Long{

        val ID =sqlDB!!.insert(dbTabla,"",values)
        return ID
    }
    fun queryCategoria(projection:Array<String>,selection:String,selectionArgs:Array<String>,orderBy:String):Cursor{

        val consulta=SQLiteQueryBuilder()
        consulta.tables=dbTabla
        val cursor=consulta.query(sqlDB,projection,selection,selectionArgs,null,null,orderBy)
        return cursor
    }
    fun borrarCategoria(selection: String,selectionArgs: Array<String>):Int{
        val contador=sqlDB!!.delete(dbTabla,selection,selectionArgs)
        return contador
    }
    fun actualizarCategoria(values: ContentValues,selection: String,selectionArgs: Array<String>):Int{
        val contador=sqlDB!!.update(dbTabla,values,selection,selectionArgs)
        return contador
    }
    @SuppressLint("Recycle")
    fun getAllCategoria():ArrayList<String>{

        val list=ArrayList<String>()
        val db:SQLiteDatabase= this.sqlDB!!
        db.beginTransaction()
        list.add("Selecione")
       try {
           val selectQuery = "SELECT * FROM " + dbTabla
           val cursor = db.rawQuery(selectQuery, null)
           if (cursor.count > 0) {
               while (cursor.moveToNext()) {
                   val cat = cursor.getString(cursor.getColumnIndex("Categoria"))
                   list.add(cat)
               }
           }
           db.setTransactionSuccessful()
       }catch (e:Exception){

           e.printStackTrace()
       }
        finally {
            db.endTransaction()
            db.close()
        }

        return list
    }



    fun insertarUser(values: ContentValues):Long{

        val ID =sqlDB!!.insert(dbTablaUser,"",values)
        return ID
    }
    fun queryUser(projection:Array<String>,selection:String,selectionArgs:Array<String>,orderBy:String): Cursor {

        val consulta= SQLiteQueryBuilder()
        consulta.tables=dbTablaUser
        val cursor=consulta.query(sqlDB,projection,selection,selectionArgs,null,null,orderBy)
        return cursor
    }
    fun borrarUser(selection: String,selectionArgs: Array<String>):Int{
        val contador=sqlDB!!.delete(dbTablaUser,selection,selectionArgs)
        return contador
    }
    fun actualizarUser(values: ContentValues, selection: String, selectionArgs: Array<String>):Int{
        val contador=sqlDB!!.update(dbTablaUser,values,selection,selectionArgs)
        return contador
    }
    fun userLogin(usuario:String,contraseña:String):Boolean{
        val query="select * from Usuarios where Usuario = '$usuario' and password = '$contraseña'"
        val cursor=sqlDB!!.rawQuery(query,null)
        if (cursor.count<=0){
            cursor.close()
            return false
        }
        cursor.close()
        return true

    }


    fun insertarIngreso(values: ContentValues):Long{

        val ID =sqlDB!!.insert(dbTablaIngresos,"",values)
        return ID
    }
    fun queryIngreso(projection:Array<String>,selection:String,selectionArgs:Array<String>,orderBy:String): Cursor {

        val consulta= SQLiteQueryBuilder()
        consulta.tables=dbTablaIngresos
        val cursor=consulta.query(sqlDB,projection,selection,selectionArgs,null,null,orderBy)
        return cursor
    }
    fun borrarIngreso(selection: String,selectionArgs: Array<String>):Int{
        val contador=sqlDB!!.delete(dbTablaIngresos,selection,selectionArgs)
        return contador
    }
    fun actualizarIngreso(values: ContentValues, selection: String, selectionArgs: Array<String>):Int{
        val contador=sqlDB!!.update(dbTablaIngresos,values,selection,selectionArgs)
        return contador
    }



    fun insertarEgreso(values:ContentValues):Long{

        val ID =sqlDB!!.insert(dbTablaEgresos,"",values)
        return ID
    }
    fun queryEgreso(projection:Array<String>,selection:String,selectionArgs:Array<String>,orderBy:String):Cursor{

        val consulta=SQLiteQueryBuilder()
        consulta.tables=dbTablaEgresos
        val cursor=consulta.query(sqlDB,projection,selection,selectionArgs,null,null,orderBy)
        return cursor
    }
    fun borrarEgreso(selection: String,selectionArgs: Array<String>):Int{
        val contador=sqlDB!!.delete(dbTablaEgresos,selection,selectionArgs)
        return contador
    }
    fun actualizarEgreso(values: ContentValues,selection: String,selectionArgs: Array<String>):Int{
        val contador=sqlDB!!.update(dbTablaEgresos,values,selection,selectionArgs)
        return contador
    }
}