package com.ameen.nytnews.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ameen.nytnews.databinding.FragmentDetailsBinding

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    override fun setupOnViewCreated(view: View) {
        TODO("Not yet implemented")
    }
}