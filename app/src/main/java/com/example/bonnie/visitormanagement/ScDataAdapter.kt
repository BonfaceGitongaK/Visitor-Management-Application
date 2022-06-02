package com.example.bonnie.visitormanagement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bonnie.visitormanagement.R
import kotlinx.android.synthetic.main.scn_layout.view.*

class ScDataAdapter (var list:ArrayList<Scanned>) :RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var value = itemView.tvValue
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.ViewHolder {
       return DataAdapter.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.scn_layout, parent, false))
    }

    override fun onBindViewHolder(holder: DataAdapter.ViewHolder, position: Int) {
        holder.value.text=list[position].value
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
