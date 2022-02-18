package com.ameen.nytnews.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ameen.nytnews.data.ResponseWrapperState
import com.ameen.nytnews.data.model.ArticleResponse
import com.ameen.nytnews.data.remote.ApiSetting
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    val articlesLiveData: MutableLiveData<ResponseWrapperState<ArticleResponse>> = MutableLiveData()

    init {
        getArticles()
    }

    fun getArticles() {

        articlesLiveData.postValue(ResponseWrapperState.Loading())

        ApiSetting.apiInstance.getArticles()
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
}