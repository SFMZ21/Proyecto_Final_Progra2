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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    lateinit var spinner : Spinner
    lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment__movimientos, container, false)

        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        //initData()

        recyclerView.adapter = ItemAdapter(initData())

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

    private fun initData() : List<Model>{
        var itemList : List<Model> = ArrayList()

        db.collection("Transacciones")
            .whereEqualTo("tag", "Ingreso")
            .get()
            .addOnSuccessListener { document ->
                for (doc in document){

                    var modelo = Model(
                        doc.data.get("tag").toString(),
                        doc.data.get("descripcion").toString(),
                        doc.data.get("monto").toString(),
                        doc.data.get("fecha").toString(),
                        doc.data.get("hora").toString())


                    (itemList as ArrayList<Model>).add(modelo)
                }
            }
        /**
        (itemList as ArrayList<Model>).add(Model("1", "hola", "2000", "12/20/2020", "14:12"))
        (itemList as ArrayList<Model>).add(Model("2", "hola", "2000", "12/20/2020", "14:12"))
        (itemList as ArrayList<Model>).add(Model("3", "hola", "2000", "12/20/2020", "14:12"))
        (itemList as ArrayList<Model>).add(Model("4", "hola", "2000", "12/20/2020", "14:12"))
        (itemList as ArrayList<Model>).add(Model("5", "hola", "2000", "12/20/2020", "14:12"))
        (itemList as ArrayList<Model>).add(Model("6", "hola", "2000", "12/20/2020", "14:12"))
        (itemList as ArrayList<Model>).add(Model("7", "hola", "2000", "12/20/2020", "14:12"))
        (itemList as ArrayList<Model>).add(Model("8", "hola", "2000", "12/20/2020", "14:12"))
        **/

        for (item in itemList){
            println("ITEMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM")
            println("${item}")
        }
        return itemList
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        listarTemp(position.toString())
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
                        //lin_layout.addView(text)
                    }

                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }

    }
}
