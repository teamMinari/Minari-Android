package com.nohjason.minari.screens.auth.data.model

data class LoginResponse(
    val status: Int,
    val message: String,
    val data: TokenData
)

data class TokenData(
    val accessToken: String,
    val refreshToken: String
)