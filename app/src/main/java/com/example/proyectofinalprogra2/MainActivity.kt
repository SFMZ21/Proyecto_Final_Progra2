package com.example.proyectofinalprogra2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment__inicio.*

class MainActivity : AppCompatActivity() {
    val manager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaccion = manager.beginTransaction()
        transaccion.replace(R.id.frame_contenedor, Fragment_Inicio() )
        transaccion.addToBackStack(null)
        transaccion.commit()
        frgIngresos()
        frgGasto()
        frgMovimientos()
        frgInicio()
        btn_inicio.setOnClickListener{frgInicio()}
        btn_ingreso.setOnClickListener{ frgIngresos()}
        btn_gasto.setOnClickListener{frgGasto()}
        btn_movimientos.setOnClickListener{frgMovimientos()}
    }

    fun frgInicio(){
        val transaccion = manager.beginTransaction()
        transaccion.replace(R.id.frame_contenedor, Fragment_Inicio() )
        transaccion.addToBackStack(null)
        transaccion.commit()
    }
    fun frgIngresos(){
        val transaccion = manager.beginTransaction()
        transaccion.replace(R.id.frame_contenedor, fragment_Ingresos() )
        transaccion.addToBackStack(null)
        transaccion.commit()
        }
    fun frgGasto(){
        val transaccion = manager.beginTransaction()
        transaccion.replace(R.id.frame_contenedor, fragment_Gasto() )
        transaccion.addToBackStack(null)
        transaccion.commit()
    }
    fun frgMovimientos(){
        val transaccion = manager.beginTransaction()
        transaccion.replace(R.id.frame_contenedor, Fragment_Movimientos() )
        transaccion.addToBackStack(null)
        transaccion.commit()
    }


}
