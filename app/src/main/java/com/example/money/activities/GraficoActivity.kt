package com.example.money.activities


import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.money.R
import com.example.money.database.DBManagerMoney
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartGestureListener
import com.github.mikephil.charting.listener.OnChartValueSelectedListener


class GraficoActivity : AppCompatActivity() {

//    var plot: PlotPlanitoXY? = null
//    var pantalla: LinearLayout? = null
//    var context: Context? = null
    var db: DBManagerMoney? = null
    lateinit var chart:LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grafico)
        chart=findViewById<LineChart>(R.id.chart)

        //chart.onChartGestureListener
        //chart.setOnChartValueSelectedListener(this)

        chart.isDragEnabled
        chart.setScaleEnabled(false)

        val ingresos:ArrayList<Entry> = ArrayList<Entry>()

        ingresos.add(Entry(1F,60f))
        ingresos.add(Entry(2F,50f))
        ingresos.add(Entry(3F,70f))
        ingresos.add(Entry(4F,30f))
        ingresos.add(Entry(5F,60f))
        ingresos.add(Entry(6F,50f))
        ingresos.add(Entry(7F,60f))
        val linea1=LineDataSet(ingresos,"Ingresos")
        linea1.color= Color.GREEN
        linea1.lineWidth= 3F
        linea1.valueTextSize=12f
        linea1.fillAlpha=110


        val egresos:ArrayList<Entry> = ArrayList<Entry>()

        egresos.add(Entry(1F,50f))
        egresos.add(Entry(2F,40f))
        egresos.add(Entry(3F,60f))
        egresos.add(Entry(4F,20f))
        egresos.add(Entry(5F,50f))
        egresos.add(Entry(6F,40f))
        egresos.add(Entry(7F,50f))
        val linea2=LineDataSet(egresos,"Gastos")
        linea2.color= Color.RED
        linea2.valueTextSize=12f
        linea2.lineWidth= 3F
        linea2.fillAlpha=110







        val dataSet=java.util.ArrayList<ILineDataSet>()
        dataSet.add(linea1)
        dataSet.add(linea2)


        val datas:LineData=LineData(dataSet)

        chart.data=datas







       /* pantalla = findViewById<LinearLayout>(R.id.pantalla)
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
        pantalla!!.addView(plot)*/
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
