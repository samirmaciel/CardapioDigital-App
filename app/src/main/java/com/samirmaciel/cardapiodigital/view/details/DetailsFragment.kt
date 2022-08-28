package com.samirmaciel.cardapiodigital.view.details

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.samirmaciel.cardapiodigital.R
import com.samirmaciel.cardapiodigital.databinding.FragmentDetailsBinding
import com.samirmaciel.cardapiodigital.domain.model.TableItem
import com.samirmaciel.cardapiodigital.view.details.viewModel.DetailsViewModel
import com.samirmaciel.cardapiodigital.view.viewModel.SharedViewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var mBinding: FragmentDetailsBinding? = null
    private var mViewModel: DetailsViewModel? = null
    private var mSharedViewModel: SharedViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBinding(view)
        setupViewModel()
        setupObservers()
        setupListeners()

        mBinding?.mlDetailsView?.transitionToEnd()
    }


    private fun setupListeners() {
        mBinding?.btnAdd?.setOnClickListener {
            mViewModel?.addProductAmount()
        }

        mBinding?.btnRemove?.setOnClickListener {
            mViewModel?.removeProductAmount()
        }

        mBinding?.btnDetailsConfirm?.setOnClickListener {
        }
    }

    private fun setupObservers() {

        mViewModel?.totalAmount?.observe(viewLifecycleOwner){ amount ->
            onChangeTableItemTotalAmount(amount)
        }
    }

    private fun onChangeTableItemTotalAmount(amount: Int){

    }

//    private fun getTableItem(): TableItem{
//     return TableItem()
//    }

    private fun setupTableItem(tableItem: TableItem) {
        mBinding?.ivDetailsImage?.setImageResource(tableItem.product.image!!)
        mBinding?.txtProductName?.text = tableItem.product.name
        mBinding?.txtProductDescription?.text = tableItem.product.description
        mBinding?.txtDetailsAmount?.text = tableItem.totalAmountSelected.toString()
        mBinding?.txtProductPrice?.text = tableItem.product.price
    }

    private fun setupViewModel() {
        mSharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        mViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    private fun setupBinding(view: View) {
        mBinding = FragmentDetailsBinding.bind(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}