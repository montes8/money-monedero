package com.example.money.fragment


import android.content.ContentValues
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView.OnItemSelectedListener
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.*
import com.example.money.R
import com.example.money.database.DBManagerMoney
import com.example.money.listas.ListaEgresos
import kotlinx.android.synthetic.main.fragment_egresos.*
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.startActivity
import java.util.*


class EgresosFragment : Fragment(), OnClickListener {


    var category: String? = null
    var fecha: TextView? = null
    var messs: TextView? = null
    var anios: TextView? = null
    var miVista: View? = null
    var conn: DBManagerMoney? = null
    var id: Int? = 0
    var baseDatos: DBManagerMoney? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        miVista = inflater!!.inflate(R.layout.fragment_egresos, container, false)
        baseDatos = DBManagerMoney(this.context)
        fecha = miVista!!.findViewById<View>(R.id.fecha_gasto) as TextView
        messs = miVista!!.findViewById<View>(R.id.mes_gasto) as TextView
        anios = miVista!!.findViewById<View>(R.id.anio_gasto) as TextView
        val desEgreso = miVista!!.findViewById<View>(R.id.descripcion_gasto)
        val montoEgreso = miVista!!.findViewById<View>(R.id.monto_gasto)


        conn = DBManagerMoney(this.context)
        val listcat = conn!!.getAllCategoria()
        val spiner = miVista!!.findViewById<View>(R.id.combo_categoria) as Spinner?
        val adapter = ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_item, listcat)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiner!!.setAdapter(adapter)
        spiner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                category = parent!!.getItemAtPosition(position).toString()
            }
        }
        val btnEngreso = miVista!!.findViewById<View>(R.id.agregar_egreso)
        mostrarFecha()
        btnEngreso.setOnClickListener(this)
        val btnVerListaEgreso = miVista!!.findViewById<View>(R.id.ver_lista_egresos)
        btnVerListaEgreso.setOnClickListener(this)

        return miVista
    }
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.agregar_egreso -> {
                val baseDatos = DBManagerMoney(this.context)
                val values = ContentValues()
                values.put("CategoriaEgreso", category.toString())
                values.put("Egreso", descripcion_gasto.text.toString())
                values.put("MontoEgreso", monto_gasto.text.toString())
                values.put("FechaEgreso", fecha_gasto.text.toString())
                values.put("MesEgreso", mes_gasto.text.toString())
                values.put("AnioEgreso", anio_gasto.text.toString())

                if (id == 0) {
                    val ID = baseDatos.insertarEgreso(values)
                    if (ID > 0) {
                        longToast("Gasto agregado correctamente")
                        descripcion_gasto.text.clear()
                        monto_gasto.text.clear()
                    } else {
                        longToast("Lo siento el gasto no se agrego correctamente ")
                    }
                } else {
                    val selectionArgs = arrayOf(id.toString())
                    val ID = baseDatos.actualizarEgreso(values, "ID=?", selectionArgs)
                    if (ID > 0) {
                        longToast("Gasto agregada correctamente")
                    } else {
                        longToast("Lo siento el gasto no se agrego correctamente ,por favor intentalo de nuevo")
                    }
                }
            }
            R.id.ver_lista_egresos -> {
                startActivity<ListaEgresos>()
            }
        }
    }

    fun mostrarFecha() {
        val c = Calendar.getInstance()
        val dia = c.get(Calendar.DAY_OF_MONTH)
        val mes = c.get(Calendar.MONTH)
        val mess = mes + 1
        val anio = c.get(Calendar.YEAR)
        //val fechas="$dia / $mess / $anio"
        fecha!!.text = dia.toString()
        messs!!.text = mess.toString()
        anios!!.text = anio.toString()
    }
}




