package com.nohjason.cheongfordo.network.response

data class TermLikes(
    val data: Like,
    val message: String,
    val status: Int
)

data class Like(
    val termDifficulty: String,
    val termExplain: String,
    val termId: Int,
    val termLike: Boolean,
    val termNm: String
)