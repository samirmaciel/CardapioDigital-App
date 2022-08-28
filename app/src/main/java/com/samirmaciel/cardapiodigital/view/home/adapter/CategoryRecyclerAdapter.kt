package com.samirmaciel.cardapiodigital.view.home.adapter

import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samirmaciel.cardapiodigital.R
import com.samirmaciel.cardapiodigital.domain.model.Category
import org.w3c.dom.Text

class CategoryRecyclerAdapter() :
    RecyclerView.Adapter<CategoryRecyclerAdapter.MyViewHolder>() {

    var itemList: List<Category> = mutableListOf()
    var onCategorySelected: OnCategorySelected<Category>? = null
    var itemCartCount: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return MyViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class MyViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtDescription = itemView.findViewById<TextView>(R.id.txt_CategoryName)
        val cardView = itemView.findViewById<CardView>(R.id.cardViewItemCategory)
        val icon = itemView.findViewById<ImageView>(R.id.iv_CategoryIcon)

        @RequiresApi(Build.VERSION_CODES.M)
        fun bindItem(position: Int) {
            Log.d("TESTE", "bindItem: " + itemCartCount)
            if(itemList[position].description.equals("Carrinho") && itemCartCount ?: 0 > 0){
                itemView.findViewById<LinearLayout>(R.id.llCategoryItemNotify).visibility = View.VISIBLE
                itemView.findViewById<TextView>(R.id.txtCategoryItemNotifyValue).text = itemCartCount?.toString()
            }else{
                itemView.findViewById<LinearLayout>(R.id.llCategoryItemNotify).visibility = View.GONE
            }

            if (itemList[position].isSelected) {
                cardView.setCardBackgroundColor(itemView.context.getColor(R.color.gray_dark))
            } else {
                cardView.setCardBackgroundColor(itemView.context.getColor(R.color.gray_light))
            }

            txtDescription.text = itemList[position].description!!
            icon.setImageResource(itemList[position].icon!!)

            itemView.setOnClickListener {
                if (!itemList[position].isSelected) {
                    cardView.setCardBackgroundColor(itemView.context.getColor(R.color.gray_dark))
                    itemList[position].isSelected = true
                    cleanSelectedCategories(itemList[position])
                } else {
                    itemList[position].isSelected = false
                    cardView.setCardBackgroundColor(itemView.context.getColor(R.color.gray_light))
                }

                onCategorySelected?.onSelected(itemList[position])
            }
        }

    }

    private fun cleanSelectedCategories(category: Category) {
        for (item in itemList) {
            if (item.description != category.description) {
                item.isSelected = false
            }
        }
        notifyDataSetChanged()
    }

    interface OnCategorySelected<T>{
        fun onSelected(result: T)
    }
}