package com.samirmaciel.cardapiodigital.view.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.samirmaciel.cardapiodigital.R
import com.samirmaciel.cardapiodigital.databinding.FragmentHomeBinding
import com.samirmaciel.cardapiodigital.domain.model.Category
import com.samirmaciel.cardapiodigital.domain.model.TableItem
import com.samirmaciel.cardapiodigital.view.home.adapter.CategoryRecyclerAdapter
import com.samirmaciel.cardapiodigital.view.home.adapter.TableItemRecyclerAdapter
import com.samirmaciel.cardapiodigital.view.home.viewModel.HomeViewModel
import com.samirmaciel.cardapiodigital.view.viewModel.SharedViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var mBinding: FragmentHomeBinding? = null
    private var mViewModel: HomeViewModel? = null
    private var mSharedViewModel: SharedViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBinding(view)
        setupViewModel()
        setupObservers()
        setupListeners()
    }


    private fun setupViewModel() {
        mViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        mSharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        mSharedViewModel?.originalTableItemList?.let {
            mSharedViewModel?.availableTableItemList?.value = mViewModel?.applyFilterTableItemsList(it)
        }

    }

    private fun setupListeners() {
        mBinding?.btnHomeFinalizeOrder?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_finalizeOrderFragment)
        }

        mBinding?.edtSearch?.doOnTextChanged { text, start, before, count ->
            mViewModel?.currentSearchText = text.toString().trim().replace("-", "")
            mSharedViewModel?.originalTableItemList?.let {
            mSharedViewModel?.availableTableItemList?.value = mViewModel?.applyFilterTableItemsList(it)
            }
        }
    }

    private fun setupBinding(view: View) {
        mBinding = FragmentHomeBinding.bind(view)
    }

    private fun setupObservers() {

        mSharedViewModel?.availableTableItemList?.observe(viewLifecycleOwner) { tableItemList ->
            setupTableItemAdapter(tableItemList)
        }

        mSharedViewModel?.categoryList?.observe(viewLifecycleOwner) { categoryList ->
            setupCategoryAdapter(categoryList)
        }

        mSharedViewModel?.isSelectedItems?.observe(viewLifecycleOwner){
            mBinding?.btnHomeFinalizeOrder?.apply {
                isEnabled = it
                setBackgroundColor(if(it) resources.getColor(android.R.color.holo_green_light) else resources.getColor(R.color.gray_light))
            }
        }
    }

    private fun setupTableItemAdapter(list: List<TableItem>) {
        val adapter = TableItemRecyclerAdapter()
        adapter.onDataChange = onDataChange
        adapter.itemList = list
        mBinding?.rvProducts?.adapter = adapter
    }

    private val onDataChange = object : TableItemRecyclerAdapter.OnDataChande<List<TableItem>>{
        override fun onChange(result: List<TableItem>) {
            mSharedViewModel?.onChangeTableItemsSelectedAmount(result)
            onTableItemChangeAmount()
        }
    }

    private val onCategorySelected = object : CategoryRecyclerAdapter.OnCategorySelected<Category>{
        override fun onSelected(result: Category) {
            if(result.isSelected){
                if(result.description.equals("Carrinho")){
                    mViewModel?.currentCategorySelected = null
                    mViewModel?.isCartSelected = true
                }else{
                    mViewModel?.isCartSelected = false
                    mViewModel?.currentCategorySelected = result
                }
            }else{
                mViewModel?.isCartSelected = false
                mViewModel?.currentCategorySelected = null
            }

            mSharedViewModel?.originalTableItemList?.let {
                mSharedViewModel?.availableTableItemList?.value = mViewModel?.applyFilterTableItemsList(it)
            }
        }

    }

    private fun onTableItemChangeAmount(){
        val adapter = mBinding?.rvCategories?.adapter as CategoryRecyclerAdapter
        var count = 0
        mSharedViewModel?.originalTableItemList?.forEach {
            if(it.totalAmountSelected > 0){
                count +=1
            }
        }

        adapter.itemCartCount = count
        adapter.notifyDataSetChanged()
    }

    private fun setupCategoryAdapter(list: List<Category>) {
        val adapter = CategoryRecyclerAdapter()
        adapter.onCategorySelected = onCategorySelected
        adapter.itemList = list
        mBinding?.rvCategories?.adapter = adapter
        mBinding?.rvCategories?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter.notifyDataSetChanged()
        onTableItemChangeAmount()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}