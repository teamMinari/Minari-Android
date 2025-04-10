package com.nohjason.minari.screens.auth.data.model

data class RegisterResponse(
    val success: Boolean,
    val status: String,
    val message: String,
    val data: String? = null
)