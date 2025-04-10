package com.nohjason.minari.screens.auth.data.model

data class RegisterRequest(
    val id: String,
    val password: String,
    val confirmPassword: String,
    val email: String
)