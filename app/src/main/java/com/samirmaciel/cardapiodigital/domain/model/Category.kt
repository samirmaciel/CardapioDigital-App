package com.samirmaciel.cardapiodigital.domain.model

data class Category(

    var id: Int? = 0,
    var description: String? = "",
    var isSelected: Boolean = false,
    var icon: Int? = 0
)
