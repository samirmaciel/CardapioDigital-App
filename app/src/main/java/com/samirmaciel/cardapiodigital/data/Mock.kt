package com.samirmaciel.cardapiodigital.data

import com.samirmaciel.cardapiodigital.R
import com.samirmaciel.cardapiodigital.domain.model.Category
import com.samirmaciel.cardapiodigital.domain.model.Product

object Mock {


    fun getProductList(): List<Product>{
        var list: MutableList<Product> = mutableListOf()
        var price = 0

        for(v in 0..10){
            list.add(Product(name= "XTudo", description = "Pão alface, tomate, ovo, bacon e molho especial", image = R.drawable.hamburguer, price = "R$9,$price"))
            price += 1
        }

        return list
    }

    fun getCategoryList(): List<Category>{
        var list: MutableList<Category> = mutableListOf()

        list.add(Category(id = 1, description = "Combo", icon = R.drawable.ic_baseline_fastfood_24))
        list.add(Category(id = 2, description = "Trio", icon = R.drawable.ic_baseline_fastfood_24))
        list.add(Category(id = 3, description = "Hamburguer", icon = R.drawable.ic_baseline_fastfood_24))
        list.add(Category(id = 4, description = "Bebidas", icon = R.drawable.ic_baseline_fastfood_24))
        list.add(Category(id = 5, description = "Batata", icon = R.drawable.ic_baseline_fastfood_24))

        return list
    }
}