package com.nohjason.minari.screens.auth.data.model

data class RefreshTokenRequest(
    val refreshToken: String
)

data class RefreshTokenResponse(
    val status: Int,
    val message: String,
    val data: RefreshTokenData
)

data class RefreshTokenData(
    val accessToken: String,
    val refreshToken: String
)
