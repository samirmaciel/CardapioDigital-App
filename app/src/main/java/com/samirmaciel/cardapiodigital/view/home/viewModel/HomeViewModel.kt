package com.samirmaciel.cardapiodigital.view.home.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samirmaciel.cardapiodigital.data.Mock
import com.samirmaciel.cardapiodigital.domain.model.Category
import com.samirmaciel.cardapiodigital.domain.model.Product
import com.samirmaciel.cardapiodigital.domain.model.TableItem
import com.samirmaciel.cardapiodigital.view.viewModel.SharedViewModel

class HomeViewModel : ViewModel(){

    var mSharedViewModel: SharedViewModel? = null
    var onResult: MutableLiveData<String?> = MutableLiveData()
    var currentSearchText : String? = null
    var isCartSelected: Boolean = false
    var currentCategorySelected: Category? = null



   fun applyFilterTableItemsList(tableItemList: List<TableItem>): List<TableItem>{
        val mList = mutableListOf<TableItem>()

        for(tableItem in tableItemList){
            var isValid = true

            if(isValid && !currentSearchText.isNullOrEmpty()){
                if(!tableItem.product.name.trim().replace("-", "").contains(currentSearchText!!, true))
                    isValid = false
            }

            if(isValid && currentCategorySelected != null){
                if(tableItem.product.category != currentCategorySelected!!.description) {
                    isValid = false
                }
            }

            if(isValid && isCartSelected){
                if(tableItem.totalAmountSelected <= 0){
                    isValid = false
                }
            }

            if(isValid){
                mList.add(tableItem)
            }
        }

       return mList
    }


}