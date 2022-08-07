package com.samirmaciel.cardapiodigital.domain.model

import android.graphics.drawable.Drawable

data class Product(

    var id: Long? = 0,
    var name: String? = "",
    var description: String? = "",
    var image: Int? = 0,
    var price: String = ""
)