package com.samirmaciel.cardapiodigital.view.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.samirmaciel.cardapiodigital.R
import com.samirmaciel.cardapiodigital.databinding.FragmentHomeBinding

class HomeFragment: Fragment(R.layout.fragment_home) {

    private var mBinding : FragmentHomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding = FragmentHomeBinding.bind(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}