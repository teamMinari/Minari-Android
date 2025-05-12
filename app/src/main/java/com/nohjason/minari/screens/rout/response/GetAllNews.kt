package com.nohjason.minari.screens.rout.response

data class GetAllNews(
    val status: Int,
    val message: String,
    val data: List<NewsData>?,
)

data class NewsData(
    val title: String?,
    val url: String?,
    val company: String?,
    val thumbnail: String?,
    val uploadTime: String?
)
