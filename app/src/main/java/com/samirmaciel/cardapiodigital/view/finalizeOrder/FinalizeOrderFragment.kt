package com.samirmaciel.cardapiodigital.view.finalizeOrder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.samirmaciel.cardapiodigital.R
import com.samirmaciel.cardapiodigital.databinding.FragmentFinalizeOrderBinding
import com.samirmaciel.cardapiodigital.view.viewModel.SharedViewModel


class FinalizeOrderFragment : Fragment(R.layout.fragment_finalize_order) {

    private var mBinding: FragmentFinalizeOrderBinding? = null
    private var mSharedViewModel: SharedViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBinding(view)
        setupViewModel()
        setupObservers()
        setupListener()
    }

    private fun setupListener() {
        mBinding?.btnFinalizeOrderBack?.setOnClickListener {
            findNavController().navigate(R.id.action_finalizeOrderFragment_to_homeFragment)
        }
    }

    private fun setupObservers() {
        mSharedViewModel?.invoice?.observe(viewLifecycleOwner){
            mBinding?.txtFinalizeOrderHeaderInvoice?.text = it.header
            mBinding?.txtFinalizeOrderCodValueInvoice?.text = it.productsCode
            mBinding?.txtFinalizeOrderDescValueInvoice?.text = it.description
            mBinding?.txtFinalizeOrderAmountUnitValueInvoice?.text = it.productsAmount
            mBinding?.txtFinalizeOrderUnitValueInvoice?.text = it.productsUnitValue
            mBinding?.txtFinalizeOrderTotalValueInvoice?.text = it.productsTotalValue
        }
    }

    private fun setupViewModel() {
        mSharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    private fun setupBinding(view: View) {
        mBinding = FragmentFinalizeOrderBinding.bind(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}