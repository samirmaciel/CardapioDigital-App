package com.samirmaciel.cardapiodigital.view.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samirmaciel.cardapiodigital.data.Mock
import com.samirmaciel.cardapiodigital.domain.model.Category
import com.samirmaciel.cardapiodigital.domain.model.Product

class HomeViewModel : ViewModel(){

    var productList: MutableLiveData<List<Product>> = MutableLiveData()
    var categoryList: MutableLiveData<List<Category>> = MutableLiveData()
    var onResult: MutableLiveData<String?> = MutableLiveData()

    init {
        loadCategories()
        loadProducts()
    }

    private fun loadProducts(){
        productList.value = Mock.getProductList()
    }

    private fun loadCategories(){
        categoryList.value = Mock.getCategoryList()
    }
}