package com.ameen.nytnews.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ameen.nytnews.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun setupOnViewCreated(view: View) {
        TODO("Not yet implemented")
    }
}