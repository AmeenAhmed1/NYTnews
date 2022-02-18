package com.ameen.nytnews.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ameen.nytnews.data.model.ArticleResult
import com.ameen.nytnews.databinding.FragmentDetailsBinding
import com.ameen.nytnews.util.loadImageUrl

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate


    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var selectedArticle: ArticleResult

    override fun setupOnViewCreated(view: View) {
        initViewWithSelectedArticle()
    }

    private fun initViewWithSelectedArticle() {
        selectedArticle = args.selectedArticle!!

        binding.apply {
            titleText.text = selectedArticle.title
            summaryText.text = selectedArticle.abstract
            publishByText.text = selectedArticle.source
            dateText.text = selectedArticle.published_date
        }

        context?.loadImageUrl(
            selectedArticle.media?.firstOrNull()?.media_metadata?.lastOrNull()?.url,
            binding.detailsImage
        )
    }
}