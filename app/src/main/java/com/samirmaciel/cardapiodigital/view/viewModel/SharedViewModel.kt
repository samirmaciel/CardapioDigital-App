package com.samirmaciel.cardapiodigital.view.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samirmaciel.cardapiodigital.data.Mock
import com.samirmaciel.cardapiodigital.domain.model.*

class SharedViewModel : ViewModel() {

    var table : Table? = null
    var availableTableItemList: MutableLiveData<List<TableItem>> = MutableLiveData()
    var originalTableItemList: List<TableItem> = mutableListOf()
    var categoryList: MutableLiveData<List<Category>> = MutableLiveData()
    var isSelectedItems: MutableLiveData<Boolean> = MutableLiveData()
    var selectedTableItemList: MutableLiveData<List<TableItem>> = MutableLiveData()
    var invoice: MutableLiveData<Invoice> = MutableLiveData()

    init {
        createTable()
        loadCategoriesList()
        loadTableItemList()
    }

    private fun loadTableItemList(){
        if(availableTableItemList.value.isNullOrEmpty()){
            availableTableItemList.value = buildTableItemList(Mock.getProductList())
            originalTableItemList = buildTableItemList(Mock.getProductList())
        }else{
            availableTableItemList.value = originalTableItemList
        }
    }

    private fun buildTableItemList(productList: List<Product>): List<TableItem>{
        val mList : MutableList<TableItem> = mutableListOf()

        for(product in productList){
            val mTableItem = TableItem(product)
            mList.add(mTableItem)
        }

        return mList
    }

    private fun loadCategoriesList(){
        categoryList.value = Mock.getCategoryList()
    }

    private fun createTable(){
        val mTable = Table()

        mTable.id = "MESA01"
        mTable.number = 1

        table = mTable
    }

    fun onChangeTableItemsSelectedAmount(tableItemList: List<TableItem>){
        var tableItemsSelectedList = mutableListOf<TableItem>()
        var invoice = Invoice()
        var invoiceHeader = StringBuilder()
        var invoiceProductsCode = StringBuilder()
        var invoiceProductsDescription = StringBuilder()
        var invoiceProductsTotalAmount= StringBuilder()
        var invoiceProductsUnitValue = StringBuilder()
        var invoiceProductsTotalValue = StringBuilder()

        for(tableItem in tableItemList){
            if(tableItem.totalAmountSelected > 0){
                tableItemsSelectedList.add(tableItem)
            }
        }

        if(tableItemsSelectedList.size > 0){
            onSelectedTableItem(tableItemsSelectedList)
            isSelectedItems.value = true

            invoiceHeader.append("Cardápio digital")
            invoiceHeader.append("\n")
            invoiceHeader.append("https://github.com/samirmaciel/CardapioDigital-App")
            invoiceHeader.append("\n")
            invoiceHeader.append("CNPJ: 00.000.000/0000-00")
            invoiceHeader.append("\n")
            invoiceHeader.append("\n")
            invoiceHeader.append("-------------------------------------------------------------------------------------------")
            invoiceHeader.append("\n")
            invoiceHeader.append("Documento auxiliar de Nota Fiscal")
            invoiceHeader.append("\n")
            invoiceHeader.append("\t\t\t\t\t\t\t\t\tde Consumidor Eletronica                 ")
            invoiceHeader.append("\n")
            invoiceHeader.append("-------------------------------------------------------------------------------------------")

            invoice.header = invoiceHeader.toString()


            for(item in tableItemsSelectedList){
                invoiceProductsCode.append(item.product.id)
                invoiceProductsCode.append("\n")
                invoiceProductsDescription.append(item.product.name)
                invoiceProductsDescription.append("\n")
                invoiceProductsTotalAmount.append(item.totalAmountSelected)
                invoiceProductsTotalAmount.append("\n")
                invoiceProductsUnitValue.append(item.product.price)
                invoiceProductsUnitValue.append("\n")
                invoiceProductsTotalValue.append(item.totalAmountSelected)
                invoiceProductsTotalValue.append("\n")
            }

            invoice.productsCode = invoiceProductsCode.toString()
            invoice.description = invoiceProductsDescription.toString()
            invoice.productsAmount = invoiceProductsTotalAmount.toString()
            invoice.productsUnitValue = invoiceProductsUnitValue.toString()
            invoice.productsTotalValue = invoiceProductsTotalValue.toString()

            this.invoice.value = invoice

        }else{
            isSelectedItems.value = false
        }

        availableTableItemList.value = tableItemList

    }

    private fun onSelectedTableItem(list: List<TableItem>){
        selectedTableItemList.value = list
    }

}