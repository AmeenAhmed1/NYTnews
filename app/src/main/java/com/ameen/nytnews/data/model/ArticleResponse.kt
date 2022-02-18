package com.ameen.nytnews.data.model

data class ArticleResponse(
    val copyright: String,
    val num_results: Int,
    val result: List<ArticleResult>,
    val status: String
)