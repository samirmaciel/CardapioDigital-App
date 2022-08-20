package com.samirmaciel.cardapiodigital.view.details.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsViewModel: ViewModel() {

    var totalAmount: MutableLiveData<Int> = MutableLiveData(0)

    fun addProductAmount(){
        totalAmount.value = totalAmount.value?.plus(1)
    }

    fun removeProductAmount(){
        if(totalAmount.value == 0) return
        totalAmount.value = totalAmount.value?.minus(1)
    }
}