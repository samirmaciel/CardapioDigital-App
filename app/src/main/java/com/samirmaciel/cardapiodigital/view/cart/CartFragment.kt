package com.samirmaciel.cardapiodigital.view.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.samirmaciel.cardapiodigital.R
import com.samirmaciel.cardapiodigital.databinding.FragmentCartBinding


class CartFragment : Fragment(R.layout.fragment_cart) {

    private var mBinding: FragmentCartBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBinding(view)
    }

    private fun setupBinding(view: View){
        mBinding = FragmentCartBinding.bind(view)
    }


    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }


}