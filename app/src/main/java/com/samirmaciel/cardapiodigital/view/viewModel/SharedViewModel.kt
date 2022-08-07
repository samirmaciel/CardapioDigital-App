package com.samirmaciel.cardapiodigital.view.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samirmaciel.cardapiodigital.domain.model.Product

class SharedViewModel : ViewModel() {

    var selectedProduct: MutableLiveData<Product> = MutableLiveData()
}