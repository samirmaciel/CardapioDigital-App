package com.samirmaciel.cardapiodigital.view.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samirmaciel.cardapiodigital.R
import com.samirmaciel.cardapiodigital.domain.model.TableItem

class TableItemRecyclerAdapter() : RecyclerView.Adapter<TableItemRecyclerAdapter.MyViewHolder>() {

    var itemList: List<TableItem> = mutableListOf()
    var onDataChange : OnDataChande<List<TableItem>>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tableitem, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(position)
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    inner class MyViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val totalAmount = itemView.findViewById<TextView>(R.id.txt_TableItemAmount)
        val txtName = itemView.findViewById<TextView>(R.id.txt_TableItemProductName)
        val txtDescription = itemView.findViewById<TextView>(R.id.txt_TableItemProductDescription)
        val txtPrice = itemView.findViewById<TextView>(R.id.txt_TableItemProductPrice)
        val btnAdd = itemView.findViewById<ImageButton>(R.id.btn_TableItemAddTotalAmount)
        val btnRemove = itemView.findViewById<ImageButton>(R.id.btn_TableItemRemoveTotalAmount)
        val image = itemView.findViewById<ImageView>(R.id.iv_TableItemProductImage)

        fun bindItem(position: Int){

            totalAmount.text = itemList[position].totalAmountSelected.toString()

            txtName.text = itemList[position].product.name
            txtDescription.text = itemList[position].product.description
            txtPrice.text = itemList[position].product.price
            image.setImageResource(itemList[position].product.image!!)

            btnAdd.setOnClickListener {
                itemList[position].totalAmountSelected += 1
                totalAmount.text = itemList[position].totalAmountSelected.toString()
                onDataChange?.onChange(itemList)
            }

            btnRemove.setOnClickListener {
                if(itemList[position].totalAmountSelected > 0){
                    itemList[position].totalAmountSelected -= 1
                    totalAmount.text = itemList[position].totalAmountSelected.toString()
                    onDataChange?.onChange(itemList)
                }

            }
        }
    }

    interface OnDataChande<T>{
        fun onChange(result: T)
    }
}


