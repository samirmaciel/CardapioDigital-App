package com.samirmaciel.cardapiodigital.view.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ShareActionProvider
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.samirmaciel.cardapiodigital.R
import com.samirmaciel.cardapiodigital.databinding.FragmentHomeBinding
import com.samirmaciel.cardapiodigital.domain.model.Category
import com.samirmaciel.cardapiodigital.domain.model.Product
import com.samirmaciel.cardapiodigital.view.home.adapter.CategoryRecyclerAdapter
import com.samirmaciel.cardapiodigital.view.home.adapter.ProductRecyclerAdapter
import com.samirmaciel.cardapiodigital.view.home.viewModel.HomeViewModel
import com.samirmaciel.cardapiodigital.view.viewModel.SharedViewModel

class HomeFragment: Fragment(R.layout.fragment_home) {

    private var mBinding : FragmentHomeBinding? = null
    private var mViewModel: HomeViewModel? = null
    private var mSharedViewModel: SharedViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBinding(view)
        setupViewModel()
        setupObservers()

    }

    override fun onResume() {
        super.onResume()
        mBinding?.mlHomeView?.transitionToEnd()
    }

    private fun setupViewModel(){
        mViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        mSharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    private fun setupBinding(view: View){
        mBinding = FragmentHomeBinding.bind(view)
    }

    private fun setupObservers(){

        mViewModel?.productList?.observe(viewLifecycleOwner){ productList ->
            setupProductAdapter(productList)
        }

        mViewModel?.categoryList?.observe(viewLifecycleOwner){ categoryList ->
            setupCategoryAdapter(categoryList)
        }
    }

    private fun setupProductAdapter(list: List<Product>){
        val adapter = ProductRecyclerAdapter{ selectedProduct ->
            mSharedViewModel?.selectedProduct?.value = selectedProduct
            mBinding?.mlHomeView?.transitionToStart()
            mBinding?.mlHomeView?.addTransitionListener(object : MotionLayout.TransitionListener{
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
                    findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
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
        adapter.itemList = list
        mBinding?.rvProducts?.adapter = adapter
        mBinding?.rvProducts?.layoutManager = GridLayoutManager(requireContext(),4)
        adapter.notifyDataSetChanged()
    }

    private fun setupCategoryAdapter(list: List<Category>){
        val adapter = CategoryRecyclerAdapter{
            Toast.makeText(requireContext(), it.description, Toast.LENGTH_SHORT).show()
        }
        adapter.itemList = list
        mBinding?.rvCategories?.adapter = adapter
        mBinding?.rvCategories?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}