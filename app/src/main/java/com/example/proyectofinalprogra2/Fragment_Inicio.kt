package com.example.proyectofinalprogra2

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var auxiliar: Int = 0

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_Inicio.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_Inicio : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var dinero: TextView? = null
    private var egreso: TextView? = null
    private var actual: TextView? = null


    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater!!.inflate(R.layout.fragment__inicio, container, false)
        dinero = view.findViewById(R.id.ingresos_total)
        egreso = view.findViewById(R.id.egreso)
        actual = view.findViewById(R.id.actual)
        calc()
        return view
    }



    fun calc() {
        var suma_gasto =0
        var aux : Int
        db.collection("Transacciones")
            .whereEqualTo("tag", "Gasto")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {

                    aux = document.data?.get("monto").toString().toInt()
                    suma_gasto=suma_gasto+aux

                }
                egreso?.text = suma_gasto.toString()
                actual?.text = suma_gasto.toString()
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
        var suma_ingreso = 0
        aux=0
        db.collection("Transacciones")
            .whereEqualTo("tag", "Ingreso")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {

                    aux = document.data?.get("monto").toString().toInt()
                    suma_ingreso=suma_ingreso+aux

                }
                dinero?.text = suma_ingreso.toString()
                actual?.text = (suma_ingreso - actual?.text.toString().toInt()).toString()
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }



    }
}