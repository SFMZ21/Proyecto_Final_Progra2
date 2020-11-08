package com.example.proyectofinalprogra2

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment__gasto.*
import kotlinx.android.synthetic.main.fragment__ingresos.*
import kotlinx.android.synthetic.main.fragment__ingresos.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_Ingresos.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_Ingresos : Fragment() {

    private val db = FirebaseFirestore.getInstance()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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


        val view: View = inflater!!.inflate(R.layout.fragment__ingresos, container, false)
        view.btn_Ingreso_guardar.setOnClickListener { income() }
        return view


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_Ingresos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_Ingresos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    fun income() {


        db.collection("Transacciones")
            .whereEqualTo("tag", "Ingreso").whereEqualTo("mes", "11")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    tituloIngreso.setText(document.data?.get("descripcion").toString())
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)

/*
                val mes: String = EdText_Ingreso_Fecha.text.toString().substring(3, 5)
                if (EdTxt_Ingreso_cantidad.text.isBlank() || EdText_Ingreso_descripcion.text.isBlank() || EdText_Ingreso_Fecha.text.isBlank() || EdText_Ingreso_Hora.text.isBlank()) {
                    Toast.makeText(
                        requireContext(),
                        "Necesita llenar todos los campos",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {

                    db.collection("Transacciones").document().set(
                        hashMapOf(
                            "monto" to EdTxt_Ingreso_cantidad.text.toString(),
                            "descripcion" to EdText_Ingreso_descripcion.text.toString(),
                            "fecha" to EdText_Ingreso_Fecha.text.toString(),
                            "hora" to EdText_Ingreso_Hora.text.toString(),
                            "tag" to "Ingreso",
                            "mes" to mes
                        )
                    )

                    Toast.makeText(requireContext(), "Ingreso exitoso", Toast.LENGTH_SHORT)
                        .show()

                    EdTxt_Ingreso_cantidad.setText("")
                    EdText_Ingreso_descripcion.setText("")
                    EdText_Ingreso_Fecha.setText("")
                    EdText_Ingreso_Hora.setText("")


                }*/

            }
    }
}