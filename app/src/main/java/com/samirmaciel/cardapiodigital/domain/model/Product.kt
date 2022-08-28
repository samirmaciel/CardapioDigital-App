package com.samirmaciel.cardapiodigital.domain.model

import android.graphics.drawable.Drawable

data class Product(

    var id: String = "",
    var name: String = "",
    var description: String = "",
    var category: String = "",
    var image: Int = 0,
    var price: String = ""
)