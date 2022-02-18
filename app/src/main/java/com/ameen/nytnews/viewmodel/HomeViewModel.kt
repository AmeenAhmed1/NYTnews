package com.ameen.nytnews.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ameen.nytnews.MyApplication
import com.ameen.nytnews.data.ResponseWrapperState
import com.ameen.nytnews.data.model.ArticleResponse
import com.ameen.nytnews.data.remote.ApiService
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var apiServiceEndPoint: ApiService

    val articlesLiveData: MutableLiveData<ResponseWrapperState<ArticleResponse>> = MutableLiveData()

    init {
        getArticles()

        (application as MyApplication).appComponent.inject(this)
    }

    fun getArticles() {

        articlesLiveData.postValue(ResponseWrapperState.Loading())

        apiServiceEndPoint.getArticles()
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
        fun factory(application: Application): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HomeViewModel(application) as T
                }
            }
        }
    }
}