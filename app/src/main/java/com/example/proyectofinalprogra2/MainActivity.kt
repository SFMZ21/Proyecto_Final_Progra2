package com.example.proyectofinalprogra2

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.example.proyectofinalprogra2.fragments.adapters.ViewPagerAdapter
import com.example.proyectofinalprogra2.fragments.fragmento_cuatro_movimientos
import com.example.proyectofinalprogra2.fragments.fragmento_dos_ingresos
import com.example.proyectofinalprogra2.fragments.fragmento_tres_gastos
import com.example.proyectofinalprogra2.fragments.fragmento_uno_balance
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpTabs()
    }

    fun setUpTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragments(fragmento_uno_balance(), "")
        adapter.addFragments(fragmento_dos_ingresos(), "")
        adapter.addFragments(fragmento_tres_gastos(), "")
        adapter.addFragments(fragmento_cuatro_movimientos(), "")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        //Tabs personalizadas -> MALAS VISTAS
        /**val txv_tab_inicio : TextView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null) as TextView
        txv_tab_inicio.setText("Inicio")
        txv_tab_inicio.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.icn_principal_inicio, 0, 0)

        val txv_tab_ingresos : TextView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null) as TextView
        txv_tab_ingresos.setText("Ingresos")
        txv_tab_ingresos.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.icn_principal_ingresos, 0, 0)

        val txv_tab_gastos : TextView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null) as TextView
        txv_tab_gastos.setText("Gastos")
        txv_tab_gastos.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.icn_principal_gasto, 0, 0)

        val txv_tab_movimientos : TextView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null) as TextView
        txv_tab_movimientos.setText("Movimientos")
        txv_tab_movimientos.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.icn_principal_movimientos, 0, 0)**/

        //Icons
        tabs.getTabAt(0)!!.setIcon(R.drawable.icn_principal_inicio)
        tabs.getTabAt(1)!!.setIcon(R.drawable.icn_principal_ingresos)
        tabs.getTabAt(2)!!.setIcon(R.drawable.icn_principal_gasto)
        tabs.getTabAt(3)!!.setIcon(R.drawable.icn_principal_movimientos)

        /**tabs.getTabAt(0)!!.customView = (txv_tab_inicio)
        tabs.getTabAt(1)!!.customView = (txv_tab_ingresos)
        tabs.getTabAt(2)!!.customView = (txv_tab_gastos)
        tabs.getTabAt(3)!!.customView = txv_tab_movimientos**/


    }

}
