package com.ameen.nytnews.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ameen.nytnews.R
import com.ameen.nytnews.adapter.ArticleAdapter
import com.ameen.nytnews.data.ResponseWrapperState
import com.ameen.nytnews.data.model.ArticleResult
import com.ameen.nytnews.data.remote.ApiService
import com.ameen.nytnews.databinding.FragmentHomeBinding
import com.ameen.nytnews.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val TAG = "HomeFragment"

    @Inject
    lateinit var apiEndPointService: ApiService

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var articleAdapter: ArticleAdapter

    override fun setupOnViewCreated(view: View) {
        initRecycler()
        initViewModel()
    }

    private fun initRecycler() {
        articleAdapter = ArticleAdapter(requireContext())
        articleAdapter.onItemClicked { navigateToDetail(it) }

        binding.homeArticleRecycler.apply {
            adapter = articleAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            hasFixedSize()
        }
    }

    private fun navigateToDetail(result: ArticleResult) {

        Log.i(TAG, "navigateToDetail: Selected -> $result")

        val bundle = Bundle()
        bundle.putSerializable("selectedArticle", result)

        findNavController().navigate(
            R.id.action_homeFragment_to_detailsFragment,
            bundle
        )
    }

    private fun initViewModel() {
        //homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel =
            ViewModelProvider(
                this,
                HomeViewModel.factory(apiEndPointService)
            )[HomeViewModel::class.java]

        homeViewModel.articlesLiveData.observe(this, Observer {
            Log.i(TAG, "initViewModel: Observer --> ${it.responseData?.results}")

            when (it) {

                is ResponseWrapperState.Loading -> {
                    binding.loadingProgressBar.visibility = View.VISIBLE
                }

                is ResponseWrapperState.Success -> {
                    articleAdapter.diff.submitList(it.responseData?.results)
                    binding.loadingProgressBar.visibility = View.GONE
                }

                is ResponseWrapperState.Error -> {
                    Toast.makeText(requireContext(), "${it.responseMessage}", Toast.LENGTH_SHORT)
                        .show()
                    Log.i(TAG, "initViewModel: Error ${it.responseMessage}")
                    binding.loadingProgressBar.visibility = View.GONE
                }
            }
        })
    }
}