package com.example.proyectofinalprogra2

import android.content.ContentValues
import android.content.ContentValues.TAG
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
import kotlinx.android.synthetic.main.fragment__movimientos.*
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
    private lateinit var test:String
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

        initData()

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

     fun initData() {
        var itemList : List<Model> = ArrayList()
         var tag:String = ""
          var descr:String = ""
            var monto:String = ""
           var fecha :String = ""
          var hora :String = ""

        db.collection("Transacciones")
            .whereEqualTo("tag", "Gasto")
            .get()
            .addOnSuccessListener { document ->
                for (doc in document){

                    Log.d(TAG, "DocumentSnapshot data: ${doc.data["tag"]}")

                    tag=doc.data["tag"].toString()
                    descr = doc.data.get("descripcion").toString()
                    monto= doc.data["monto"].toString()
                    fecha = doc.data["fecha"].toString()
                    hora = doc.data["hora"].toString()
                    (itemList as ArrayList<Model>).add(Model(tag, descr, monto, fecha, hora))
                    recyclerView.adapter = ItemAdapter(itemList)

                }
            }




    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        recyclerView.removeAllViewsInLayout()

        if (chckIngreso.isChecked && !chckGasto.isChecked){
            //Toast.makeText(requireContext(), "Ingreso", Toast.LENGTH_SHORT).show()
            println("****************************************************INGRESO***************************************************")
            if (position.toString().length < 2){
                listarTemp("0$position","Ingreso")
            }else{
                listarTemp(position.toString(),"Ingreso")
            }
        }

        if (chckGasto.isChecked && !chckIngreso.isChecked){
            println("****************************************************GASTO***************************************************$position")
            if (position.toString().length < 2){
                listarTemp("0$position","Gasto")
            }else{
                listarTemp(position.toString(),"Gasto")
            }
        }

        if (chckGasto.isChecked && chckIngreso.isChecked){
            println("****************************************************GASTO e INGRESO***************************************************$position")
            if (position.toString().length < 2){
                listarTempDouble("0$position")
            }else{
                listarTempDouble(position.toString())
            }
        }

    }

    fun listarTempDouble(mes : String){
        var itemList : List<Model> = ArrayList()
        var tag:String = ""
        var descr:String = ""
        var monto:String = ""
        var fecha :String = ""
        var hora :String = ""


        println("mes************************************$mes")

        db.collection("Transacciones")
            .whereEqualTo("mes", mes)
            .get()
            .addOnSuccessListener { document ->
                for (doc in document){

                    //Log.d(TAG, "DocumentSnapshot data: ${doc.data["tag"]}")

                    tag=doc.data["tag"].toString()
                    descr = doc.data.get("descripcion").toString()
                    monto= doc.data["monto"].toString()
                    fecha = doc.data["fecha"].toString()
                    hora = doc.data["hora"].toString()
                    (itemList as ArrayList<Model>).add(Model(tag, descr, monto, fecha, hora))
                    recyclerView.adapter = ItemAdapter(itemList)

                }
            }

    }

    fun listarTemp(mes : String , ttaagg : String){
        var itemList : List<Model> = ArrayList()
        var tag:String = ""
        var descr:String = ""
        var monto:String = ""
        var fecha :String = ""
        var hora :String = ""

        println("tag************************************$ttaagg")
        println("mes************************************$mes")

        db.collection("Transacciones")
            .whereEqualTo("tag", ttaagg)
            .whereEqualTo("mes", mes)
            .get()
            .addOnSuccessListener { document ->
                for (doc in document){

                    //Log.d(TAG, "DocumentSnapshot data: ${doc.data["tag"]}")

                    tag=doc.data["tag"].toString()
                    descr = doc.data.get("descripcion").toString()
                    monto= doc.data["monto"].toString()
                    fecha = doc.data["fecha"].toString()
                    hora = doc.data["hora"].toString()
                    (itemList as ArrayList<Model>).add(Model(tag, descr, monto, fecha, hora))
                    recyclerView.adapter = ItemAdapter(itemList)

                }
            }

    }
}
