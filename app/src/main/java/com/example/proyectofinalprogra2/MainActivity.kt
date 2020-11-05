package com.example.proyectofinalprogra2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment__gasto.*
import kotlinx.android.synthetic.main.fragment__ingresos.*


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
        frgInicio()
        frgMovimientos()
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

    fun income(view: View){

        val ingreso = EdTxt_Ingreso_cantidad.text.toString().toInt()
        val descrip = EdText_Ingreso_descripcion.text.toString()
        val fecha = EdText_Ingreso_Fecha.text.toString()
        val hora = EdText_Ingreso_Hora.text.toString()

        println(ingreso)
        println(descrip)
        println(fecha)
        println(hora)

        Toast.makeText(this, "Ingreso registrado con exito", Toast.LENGTH_SHORT).show()
        frgInicio()




    }

    fun outcome(view: View){
        val ingreso = EdTxt_Gasto_cantidad.text.toString().toInt()
        val descrip = EdText_Gasto_descripcion.text.toString()
        val fecha = EdText_Gasto_Fecha.text.toString()
        val hora = EdText_Gasto_descripcion_Hora.text.toString()

        println(ingreso)
        println(descripcion)
        println(fecha)
        println(hora)

        Toast.makeText(this, "Gasto registrado con exito", Toast.LENGTH_SHORT).show()



    }

}
