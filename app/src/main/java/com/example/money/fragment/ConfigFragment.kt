package com.example.money.fragment



import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.money.R
import com.example.money.activities.CategoriaActivity
import com.example.money.activities.GraficoActivity
import com.example.money.listas.ListaUsuario
import org.jetbrains.anko.support.v4.startActivity


class ConfigFragment : Fragment(), View.OnClickListener {

    var miVista: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        miVista = inflater!!.inflate(R.layout.fragment_config, container, false)
        val btnCategoria = miVista!!.findViewById<View>(R.id.button_categoria)
        btnCategoria.setOnClickListener(this)
        val btnVerCategoria = miVista!!.findViewById<View>(R.id.ver_categoria)
        btnVerCategoria.setOnClickListener(this)
        val btnGrafico = miVista!!.findViewById<View>(R.id.button_grafico)
        btnGrafico.setOnClickListener(this)

        return miVista
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.button_categoria -> startActivity<CategoriaActivity>()
            R.id.ver_categoria -> startActivity<ListaUsuario>()
            R.id.button_grafico -> startActivity<GraficoActivity>()
        }
    }


}
