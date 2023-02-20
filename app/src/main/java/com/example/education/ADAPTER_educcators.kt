package com.example.education

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ADAPTER_educcators(private val educatorlist: ArrayList<dataclasseducators>) :
    RecyclerView.Adapter<ADAPTER_educcators.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ADAPTER_educcators.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.educatorslist,parent,false)
        return ADAPTER_educcators.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ADAPTER_educcators.ViewHolder, position: Int) {
        val currentitem = educatorlist[position]
        holder.educatorname.text = currentitem.passcode
        holder.location.text = currentitem.username
    }

    override fun getItemCount(): Int {
        return educatorlist.size
    }



    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){

        val educatorname = itemView.findViewById<TextView>(R.id.Name1)
        val location = itemView.findViewById<TextView>(R.id.location)
    }


}