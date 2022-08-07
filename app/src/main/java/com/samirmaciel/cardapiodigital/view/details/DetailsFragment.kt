package com.samirmaciel.cardapiodigital.view.details

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.samirmaciel.cardapiodigital.R
import com.samirmaciel.cardapiodigital.databinding.FragmentDetailsBinding
import com.samirmaciel.cardapiodigital.domain.model.Product
import com.samirmaciel.cardapiodigital.view.viewModel.SharedViewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var mBinding: FragmentDetailsBinding? = null
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
            mBinding?.mlDetailsView?.transitionToStart()
            mBinding?.mlDetailsView?.addTransitionListener(object : MotionLayout.TransitionListener{
                override fun onTransitionStarted(
                    motionLayout: MotionLayout?,
                    startId: Int,
                    endId: Int
                ) {
                }

                override fun onTransitionChange(
                    motionLayout: MotionLayout?,
                    startId: Int,
                    endId: Int,
                    progress: Float
                ) {
                }

                override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                    findNavController().navigate(R.id.action_detailsFragment_to_homeFragment)
                }

                override fun onTransitionTrigger(
                    motionLayout: MotionLayout?,
                    triggerId: Int,
                    positive: Boolean,
                    progress: Float
                ) {
                }

            })

        }
    }

    private fun setupObservers() {
        mSharedViewModel?.selectedProduct?.observe(viewLifecycleOwner){ selectedProduct ->
            setupProduct(selectedProduct)
        }
    }

    private fun setupProduct(selectedProduct: Product) {
        mBinding?.ivDetailsImage?.setImageResource(selectedProduct.image!!)
        mBinding?.txtProductName?.text = selectedProduct.name
        mBinding?.txtProductDescription?.text = selectedProduct.description
        mBinding?.txtProductPrice?.text = selectedProduct.price
    }

    private fun setupViewModel() {
        mSharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    private fun setupBinding(view: View) {
        mBinding = FragmentDetailsBinding.bind(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}