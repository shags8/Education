package com.example.education

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ADAPTER(private val courselist: ArrayList<dataclassCourse>) :
    RecyclerView.Adapter<ADAPTER.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.courselist,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = courselist[position]
        holder.coursename.text = currentitem.coursename
        holder.author.text = currentitem.authorname
    }

    override fun getItemCount(): Int {
        return  courselist.size
    }


    class MyViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){

        val coursename = itemView.findViewById<TextView>(R.id.Coursename)
        val author = itemView.findViewById<TextView>(R.id.authorname)
    }


}