package com.samirmaciel.cardapiodigital.view.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samirmaciel.cardapiodigital.R
import com.samirmaciel.cardapiodigital.domain.model.Category

class CategoryRecyclerAdapter(val onClick : (Category) -> Unit) :
    RecyclerView.Adapter<CategoryRecyclerAdapter.MyViewHolder>() {

    var itemList: List<Category> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class MyViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtDescription = itemView.findViewById<TextView>(R.id.txt_CategoryName)
        val icon = itemView.findViewById<ImageView>(R.id.iv_CategoryIcon)

        fun bindItem(position: Int){
            txtDescription.text = itemList[position].description!!
            icon.setImageResource(itemList[position].icon!!)

            itemView.setOnClickListener {
                onClick(itemList[position])
            }
        }

    }
}