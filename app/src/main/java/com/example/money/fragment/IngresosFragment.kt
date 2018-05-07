package com.example.money.fragment


import android.content.ContentValues
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import com.example.money.R
import com.example.money.database.DBManagerMoney
import com.example.money.listas.ListaIngresos
import kotlinx.android.synthetic.main.fragment_ingresos.*
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.startActivity
import java.util.*


class IngresosFragment : Fragment(), OnClickListener{

   var fechaIngreso:TextView? = null
    var mesi:TextView? = null
    var aniosi:TextView? = null
    var tota:TextView? = null
    var miVista:View?=null
    var id:Int?=0
    var baseDatos: DBManagerMoney? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        miVista=inflater!!.inflate(R.layout.fragment_ingresos, container, false)
        baseDatos= DBManagerMoney(this.context)
        fechaIngreso = miVista!!.findViewById<View>(R.id.edit_fecha_ingreso) as TextView?
        mesi = miVista!!.findViewById<View>(R.id.edit_mes_ingreso) as TextView?
        aniosi = miVista!!.findViewById<View>(R.id.edit_anio_ingreso) as TextView?
        val textIngreso=miVista!!.findViewById<View>(R.id.descripcion_ingreso)
        val textMonto=miVista!!.findViewById<View>(R.id.monto_ingreso)
        val btnIngreso=miVista!!.findViewById<View>(R.id.agregar_ingreso)
        mostrarFechaIngreso()
        btnIngreso.setOnClickListener(this)
        val btnVerLista=miVista!!.findViewById<View>(R.id.ver_lista_ingresos)
        btnVerLista.setOnClickListener(this)


        return miVista
    }
    fun mostrarFechaIngreso(){

        val c= Calendar.getInstance()
        val dia=c.get(Calendar.DAY_OF_MONTH)
        val mes=c.get(Calendar.MONTH)
        val mess=mes+1
        val anio=c.get(Calendar.YEAR)
        //val fechas="$dia / $mess / $anio"
        fechaIngreso!!.text= dia.toString()
        mesi!!.text=mess.toString()
        aniosi!!.text=anio.toString()
    }




    override fun onClick(v: View?) {
        when (v!!.id){

            R.id.agregar_ingreso  ->{
                val baseDatos= DBManagerMoney(this.context)
                val values = ContentValues()
                values.put("Ingreso",descripcion_ingreso.text.toString())
                values.put("MontoIngreso",monto_ingreso.text.toString())
                values.put("FechaIngreso",edit_fecha_ingreso.text.toString())
                values.put("MesIngreso",edit_mes_ingreso.text.toString())
                values.put("AnioIngreso",edit_anio_ingreso.text.toString())

                if (id==0) {
                    val ID = baseDatos.insertarIngreso(values)
                    if (ID > 0) {
                        longToast("Ingreso agregado correctamente")
                        descripcion_ingreso.text.clear()
                        monto_ingreso.text.clear()
                    } else {
                        longToast("Lo siento el Ingreso no se agrego correctamente ")
                    }
                }else{
                    val selectionArgs = arrayOf(id.toString())
                    val ID = baseDatos.actualizarIngreso(values,"ID=?",selectionArgs)
                    if (ID > 0) {
                        longToast("Ingreso agregada correctamente")
                    } else {
                        longToast("Lo siento el Ingreso no se agrego correctamente ,por favor intentalo de nuevo")
                    }
                }

            }

            R.id.ver_lista_ingresos ->{startActivity<ListaIngresos>()}
        }
    }
}
