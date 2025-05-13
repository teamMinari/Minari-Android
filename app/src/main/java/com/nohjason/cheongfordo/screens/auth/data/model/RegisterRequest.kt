package com.nohjason.cheongfordo.screens.auth.data.model

data class RegisterRequest(
    val id: String,
    val password: String,
    val confirmPassword: String,
    val email: String
)