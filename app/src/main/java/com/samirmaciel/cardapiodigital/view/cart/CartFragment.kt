package com.samirmaciel.cardapiodigital.view.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.samirmaciel.cardapiodigital.R
import com.samirmaciel.cardapiodigital.databinding.FragmentCartBinding
import com.samirmaciel.cardapiodigital.domain.model.TableItem
import com.samirmaciel.cardapiodigital.view.home.adapter.TableItemRecyclerAdapter
import com.samirmaciel.cardapiodigital.view.viewModel.SharedViewModel


class CartFragment : Fragment(R.layout.fragment_cart) {

    private var mBinding: FragmentCartBinding? = null
    private var mSharedViewModel: SharedViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBinding(view)
        setupViewModels()
        setupListeners()
        setupObservers()
    }

    private fun setupViewModels() {
        mSharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    private fun setupObservers() {
        mSharedViewModel?.isSelectedItems?.observe(viewLifecycleOwner){

        }

        mSharedViewModel?.selectedTableItemList?.observe(viewLifecycleOwner){
            setupTableItemsRecycler(it)
        }

        mSharedViewModel?.invoice?.observe(viewLifecycleOwner){ invoice ->

        }
    }

    private fun setupTableItemsRecycler(list: List<TableItem>){
        val adapter = TableItemRecyclerAdapter()
        adapter.itemList = list
        mBinding?.rvCartSelectedTableItems?.adapter = adapter
    }

    private fun setupBinding(view: View){
        mBinding = FragmentCartBinding.bind(view)
    }

    private fun setupListeners(){
        mBinding?.btnCartBack?.setOnClickListener {
            findNavController().navigateUp()
        }

        mBinding?.btnCartConfirm?.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_finalizeOrderFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }


}