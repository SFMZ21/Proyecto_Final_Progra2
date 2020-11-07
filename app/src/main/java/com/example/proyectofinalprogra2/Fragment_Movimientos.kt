package com.example.proyectofinalprogra2

import android.content.ContentValues
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.api.Distribution
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_Movimientos.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_Movimientos : Fragment(), AdapterView.OnItemSelectedListener {

    private val db = FirebaseFirestore.getInstance()

    //Variables
    lateinit var lin_layout : LinearLayout
    lateinit var txtView1 : TextView
    lateinit var spinner : Spinner
    var arr_txv = ArrayList<TextView>()

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
        val view: View = inflater!!.inflate(R.layout.fragment__movimientos, container, false)

        lin_layout = view.findViewById(R.id.frgMov_ScrollContainer)
        txtView1 = TextView(requireContext())
        txtView1.gravity = 1

        // creations
        spinner = view.findViewById<Spinner>(R.id.frgMov_SpnMes)
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.meses,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
        return view

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        lin_layout.removeAllViews()

        listarTemp(position.toString())

        for (txv in arr_txv){
            lin_layout.addView(txv)
        }

        //txtView1.text = position.toString()
        //lin_layout.addView(txtView1)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment_Movimientos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_Movimientos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


//    fun listar(tag: String, mes: String) : ArrayList<String> {
//        var descriptions = ArrayList<String>()
//        db.collection("Transacciones")
//            //.whereEqualTo("tag", tag)
//            .whereEqualTo("mes", mes)
//            .get()
//            .addOnSuccessListener { documents ->
//                for (document in documents){
//                    descriptions.add(document.data?.get("descripcion").toString())
//                    println("DATOS*********************** ${document.data?.get("descripcion").toString()}")
//                }
//            }
//
//        return descriptions
//    }

    fun listarTemp(mes : String){
        db.collection("Transacciones")
            .whereEqualTo("tag", "Ingreso")
            //.whereEqualTo("mes", mes)
            .get()
            .addOnSuccessListener { documents ->

                for (document in documents){

                    if (!TextUtils.isEmpty(document.data?.get("descripcion").toString())){

                        val text = TextView(requireContext())
                        text.text = document.data!!.get("descripcion").toString()
                        lin_layout.addView(text)
                    }

                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }

    }
}
