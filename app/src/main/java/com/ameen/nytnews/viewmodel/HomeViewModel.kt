package com.ameen.nytnews.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ameen.nytnews.data.ResponseWrapperState
import com.ameen.nytnews.data.model.ArticleResponse
import com.ameen.nytnews.data.remote.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val apiEndPoint: ApiService
) : ViewModel() {

    val articlesLiveData: MutableLiveData<ResponseWrapperState<ArticleResponse>> = MutableLiveData()

    init {
        getArticles()
    }

    fun getArticles() {

        articlesLiveData.postValue(ResponseWrapperState.Loading())

        apiEndPoint.getArticles()
            .observeOn(Schedulers.io())
            .subscribe(
                { articleResponse ->
                    articlesLiveData.postValue(ResponseWrapperState.Success(data = articleResponse))
                },

                { errorMessage ->
                    articlesLiveData.postValue(ResponseWrapperState.Error(message = errorMessage.message))
                }

            )
    }


    companion object {
        fun factory(apiEndPoint: ApiService): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HomeViewModel(apiEndPoint) as T
                }
            }
        }
    }

}