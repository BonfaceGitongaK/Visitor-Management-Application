package com.example.bonnie.visitormanagement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bonnie.visitormanagement.R
import kotlinx.android.synthetic.main.rv_layout.view.*
import kotlinx.android.synthetic.main.scn_layout.view.*


class DataAdapter (var list:ArrayList<Visitors>) :RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val value=itemView.tvValue
        var email=itemView.tvEmail
        var id=itemView.tvId
        var name=itemView.tvName
        var phone=itemView.tvPhone

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.email.text=list[position].email
        holder.id.text= list[position].id.toString()
        holder.name.text=list[position].name
        holder.phone.text=list[position].phone.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}