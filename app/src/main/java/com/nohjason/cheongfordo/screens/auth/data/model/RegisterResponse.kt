package com.nohjason.cheongfordo.screens.auth.data.model

data class RegisterResponse(
    val success: Boolean,
    val status: String,
    val message: String,
    val data: String? = null
)