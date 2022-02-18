package com.ameen.nytnews.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ameen.nytnews.data.model.ArticleResponse
import com.ameen.nytnews.data.remote.ApiSetting
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.math.log

class HomeViewModel : ViewModel() {

    private val TAG = "HomeViewModel"

    val articlesLiveData: MutableLiveData<ArticleResponse> = MutableLiveData()

    init {
        getArticles()
    }

    fun getArticles(): Disposable =
        ApiSetting.apiInstance.getArticles()
            .observeOn(Schedulers.io())
            .subscribe(
                { articleResponse ->
                    Log.i(TAG, "getArticles: Result Size List -> ${articleResponse}")
                    articlesLiveData.postValue(articleResponse)
                },
                {

                }

            )
}