package com.example.money.activities


import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.money.R
import com.example.money.database.DBManagerMoney
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


class GraficoActivity : AppCompatActivity() {


    var db: DBManagerMoney? = null
    lateinit var chart:LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grafico)
        chart=findViewById<LineChart>(R.id.chart)


//AGREGA UN ALUINEA DE LIMITES AL GRAFICO
        val vpper_limit= LimitLine(65f,"Danger")
        vpper_limit.lineWidth=4f
        vpper_limit.enableDashedLine(10f,10f,0f)
        vpper_limit.labelPosition=LimitLine.LimitLabelPosition.RIGHT_TOP
        vpper_limit.textSize=15f
        val lower_limit=LimitLine(65f,"Danger")
        lower_limit.lineWidth=4f
        lower_limit.enableDashedLine(10f,10f,0f)
        lower_limit.labelPosition=LimitLine.LimitLabelPosition.RIGHT_TOP
        lower_limit.textSize=15f
        val leftAxis=chart.axisLeft
        leftAxis.removeAllLimitLines()
        leftAxis.addLimitLine(vpper_limit)
        leftAxis.addLimitLine(lower_limit)
        leftAxis.axisMaximum=100f
        leftAxis.axisMinimum=25f
        leftAxis.setDrawLimitLinesBehindData(true)
        chart.axisRight.isEnabled=false
        chart.onChartGestureListener


        chart.isDragEnabled
        chart.setScaleEnabled(false)
////////////////////////////////////////////////////////////
        val ingresos:ArrayList<Entry> = ArrayList<Entry>()
        ingresos.add(Entry(0F,60f))
        ingresos.add(Entry(1F,50f))
        ingresos.add(Entry(2F,70f))
        ingresos.add(Entry(3F,30f))
        ingresos.add(Entry(4F,60f))
        val linea1=LineDataSet(ingresos,"Ingresos")
        linea1.color= Color.GREEN
        linea1.lineWidth= 3F
        linea1.valueTextSize=12f
        linea1.fillAlpha=110

/////////////////////////////////////////////////////////
        val egresos:ArrayList<Entry> = ArrayList<Entry>()
        egresos.add(Entry(0F,50f))
        egresos.add(Entry(1F,40f))
        egresos.add(Entry(2F,60f))
        egresos.add(Entry(3F,20f))
        egresos.add(Entry(4F,50f))

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



//AGREGAMOS
        val values:ArrayList<String> = ArrayList()
        values.add("Enero")
        values.add("Febrero")
        values.add("Marzo")
        values.add("Abril")
        values.add("Mayo")
        val xAxis=chart.xAxis
        xAxis.valueFormatter = MiAxis(values)

    }



    class MiAxis(values: ArrayList<String>) : IAxisValueFormatter{
        var mvalues:ArrayList<String> = values

        override fun getFormattedValue(value: Float, axis: AxisBase?): String {


            return mvalues[value.toInt()]
        }

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

