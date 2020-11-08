package com.example.proyectofinalprogra2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(initData: List<Model>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    var itemList1 : List<Model> = initData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.card_molde, parent, false)
        val viewHolder : ViewHolder = ViewHolder(view)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        holder.itemTag.setText(itemList1.get(position).tag)
        holder.itemDesc.setText(itemList1.get(position).descripcion)
        holder.itemMonto.setText(itemList1.get(position).monto)
        holder.itemFecha.setText(itemList1.get(position).fecha)
        holder.itemHora.setText(itemList1.get(position).hora)

    }

    override fun getItemCount(): Int {
        return itemList1.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemTag : TextView = itemView.findViewById(R.id.txtTag)
        var itemDesc : TextView = itemView.findViewById(R.id.txtDescripcion)
        var itemMonto : TextView = itemView.findViewById(R.id.txtMonto)
        var itemFecha : TextView = itemView.findViewById(R.id.txtFecha)
        var itemHora : TextView = itemView.findViewById(R.id.txtHora)


    }
}