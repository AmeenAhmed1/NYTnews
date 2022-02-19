package com.ameen.nytnews.ui.fragment

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ameen.nytnews.databinding.FragmentFullViewBinding
import com.ameen.nytnews.util.loadImageUrl

class FullViewFragment : BaseFragment<FragmentFullViewBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFullViewBinding
        get() = FragmentFullViewBinding::inflate

    private val args: FullViewFragmentArgs by navArgs()

    override fun setupOnViewCreated(view: View) {
        context?.loadImageUrl(
            args.selectedImage,
            binding.zoomImageView
        )

        binding.buttonShare.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
            }

            intent.putExtra(Intent.EXTRA_TEXT, args.selectedImage)

            startActivity(Intent.createChooser(intent, "Share your Image using:"))

        }
    }
}