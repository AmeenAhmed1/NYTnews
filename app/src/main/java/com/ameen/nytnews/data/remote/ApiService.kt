package com.ameen.nytnews.data.remote

import com.ameen.nytnews.BuildConfig
import com.ameen.nytnews.data.model.ArticleResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    /**
     * @param period is 1, 7 or 30 days.
     * @param apiKey Your Developer API Key.
     * Default is 1
     */
    @GET("/viewed/{period}.json")
    fun getArticles(
        @Path("period") period: Int = 1,
        @Query("api-key") apiKey: String = BuildConfig.WEBSERVICE_API_KEY
    ): Observable<ArticleResponse>
}