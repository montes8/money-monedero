package com.example.money.activities


import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.example.money.R
import com.example.money.database.DBManagerMoney
import com.juang.jplot.PlotPlanitoXY


class GraficoActivity : AppCompatActivity() {

    var plot: PlotPlanitoXY? = null
    var pantalla: LinearLayout? = null
    var context: Context? = null
    var db: DBManagerMoney? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grafico)
        pantalla = findViewById<LinearLayout>(R.id.pantalla)
        context = this
        db = DBManagerMoney(this)
        val x = floatArrayOf(2f, 3f, 4f, 5f)
        val y = floatArrayOf(4f, 5f, 6f, 9f)
        val a = floatArrayOf(2f, 3f, 4f, 5f)
        val b = floatArrayOf(3f, 4f, 5f, 8f)


        plot = PlotPlanitoXY(context, "Grafico de Ingresos y Gastos", "Ingresos", "gastos")
        plot!!.SetSerie1(x, y, "Ingresos", 5, true)//x and y simpre en ese orden,grap1=nombre de la leyenda,5=tamaño del punto,true=si une losmpuntos con un alinea
        plot!!.SetHD(true)//suavise ls border
        plot!!.SetTouch(true)//funcione con el touch
        plot!!.SetSizeLeyend(20)
        plot!!.SetSizeTitulo(40)
        plot!!.SetSerie2(a, b, "Egresos", 5, true, 1)
        pantalla!!.addView(plot)
    }


    @SuppressLint("Recycle")
    fun sumaIngresos(): String {
        val lista = ArrayList<Int>()
        val dbs: SQLiteDatabase = db!!.sqlDB!!
        dbs.beginTransaction()
        var total: Int = 0
        val selectQuery = "SELECT * FROM " + db!!.dbTablaIngresos
        val cursor = dbs.rawQuery(selectQuery, null)
        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                val cat = cursor.getString(cursor.getColumnIndex("MontoIngreso")).toInt()
                lista.add(cat)
            }
        }
        for (pocition in lista.indices) {
            total = total + lista.get(pocition)
        }
        return total.toString()
    }
}
