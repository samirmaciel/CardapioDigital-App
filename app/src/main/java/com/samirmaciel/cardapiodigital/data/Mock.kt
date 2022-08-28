package com.samirmaciel.cardapiodigital.data

import com.samirmaciel.cardapiodigital.R
import com.samirmaciel.cardapiodigital.domain.model.Category
import com.samirmaciel.cardapiodigital.domain.model.Product

object Mock {


    fun getProductList(): List<Product>{
        var list: MutableList<Product> = mutableListOf()
        var price = 0

        list.add(Product(id = "COM001", name= "Combo 1", description = "Combo com X-Tudo, refrigerante coca-cola no refil e batata frita",category = "Combo", image = R.drawable.combo_1, price = "R$15,50"))
        list.add(Product(id = "COM002",name= "Combo 2", description = "Combo com X-Tudo2, refrigerante coca-cola lata e batata frita",category = "Combo", image = R.drawable.combo_2, price = "R$14,30"))
        list.add(Product(id = "COM003",name= "Combo 3", description = "Combo com dois hamburguers, um sanduiche e batata frita grande",category = "Combo", image = R.drawable.combo_3, price = "R$18,20"))
        list.add(Product(id = "COM004",name= "Combo 4", description = "Combo com três X-Tudo", category = "Combo",image = R.drawable.combo_4, price = "R$18,99"))
        list.add(Product(id = "HAM001",name= "X-Tudo", description = "Hamburguer mais popular com varios ingredientes de qualidade", category = "Hamburguer",image = R.drawable.hamburguer, price = "R$10,29"))
        list.add(Product(id = "HAM002",name= "X-Egg", description = "Hamburguer X-Egg", category = "Hamburguer",image = R.drawable.hamburguer_xegg, price = "R$8,29"))
        list.add(Product(id = "HAM003",name= "X-Picanha", description = "Hamburguer X-Picanha a carne bovina main stream.", category = "Hamburguer",image = R.drawable.hamburguer_xpicanha, price = "R$12,29"))
        list.add(Product(id = "HAM004",name= "X-BigPicanha", description = "Hamburguer X-BigPicanha com muito mais picanha que o X-Picanha.", category = "Hamburguer",image = R.drawable.hamburguer_bigpicanha, price = "R$16,79"))
        list.add(Product(id = "HAM005",name= "X-Salada", description = "Hamburguer X-Salada que vem salada.",category = "Hamburguer", image = R.drawable.hamburguer_xsalada, price = "R$4,69"))
        list.add(Product(id = "BEB001",name= "Coca-cola", description = "Refrigerante Coca-cola lata 300ml", category = "Bebida",image = R.drawable.bebida_coca, price = "R$2,29"))
        list.add(Product(id = "BEB002",name= "Pepsi", description = "Refrigerante Pepsi lata 300ml", category = "Bebida",image = R.drawable.bebida_pepsi, price = "R$2,29"))
        list.add(Product(id = "BEB003",name= "Sprite", description = "Refrigerante Sprite lata 300ml",category = "Bebida", image = R.drawable.bebida_sprite, price = "R$3,29"))
        list.add(Product(id = "BEB004",name= "RedBull", description = "Energético RedBull lata 300ml", category = "Bebida",image = R.drawable.bebida_redbull, price = "R$2,29"))
        list.add(Product(id = "POR001",name= "Porção 1", description = "Porção de frango frito", category = "Porção",image = R.drawable.porcao_frangofrito, price = "R$6,59"))
        list.add(Product(id = "POR002",name= "Porção 2", description = "Porção de frango frito ao molho", category = "Porção",image = R.drawable.porcao_frangofritoaomolho, price = "R$8,29"))
        list.add(Product(id = "BAT001",name= "Batata pequena", description = "Batata frita pequena", category = "Batata",image = R.drawable.batata_pequena, price = "R$4,29"))
        list.add(Product(id = "BAT002",name= "Batata média", description = "Batata frita média", category = "Batata",image = R.drawable.batata_media, price = "R$6,29"))
        list.add(Product(id = "BAT003",name= "Batata grande", description = "Batata frita grande", category = "Batata",image = R.drawable.batata_grande, price = "R$8,29"))


        return list
    }

    fun getCategoryList(): List<Category>{
        var list: MutableList<Category> = mutableListOf()

        list.add(Category(id = 1, description = "Combo", icon = R.drawable.ic_baseline_fastfood_24))
        list.add(Category(id = 3, description = "Hamburguer", icon = R.drawable.ic_hamburguer))
        list.add(Category(id = 4, description = "Bebida", icon = R.drawable.ic_bebida))
        list.add(Category(id = 5, description = "Batata", icon = R.drawable.ic_batata))
        list.add(Category(id = 5, description = "Porção", icon = R.drawable.ic_porcao))
        list.add(Category(id = 5, description = "Carrinho", icon = R.drawable.ic_baseline_shopping_cart_24))

        return list
    }
}