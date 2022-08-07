package com.samirmaciel.cardapiodigital.view.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samirmaciel.cardapiodigital.R
import com.samirmaciel.cardapiodigital.domain.model.Product

class ProductRecyclerAdapter(val onAddItem : (Product) -> Unit) : RecyclerView.Adapter<ProductRecyclerAdapter.MyViewHolder>() {

    var itemList: List<Product> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(position)
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    inner class MyViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName = itemView.findViewById<TextView>(R.id.txt_ProductName)
        val txtDescription = itemView.findViewById<TextView>(R.id.txt_ProductDescription)
        val txtPrice = itemView.findViewById<TextView>(R.id.txt_ProductPrice)
        val btnAdd = itemView.findViewById<ImageButton>(R.id.btn_Add)
        val image = itemView.findViewById<ImageView>(R.id.iv_ProductImage)

        fun bindItem(position: Int){
            txtName.text = itemList[position].name
            txtDescription.text = itemList[position].description
            txtPrice.text = itemList[position].price
            image.setImageResource(itemList[position].image!!)

            btnAdd.setOnClickListener {
                onAddItem(itemList[position])
            }
        }
    }
}


