package com.ameen.nytnews.data.model

data class ArticleResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<ArticleResult>,
    val status: String
)