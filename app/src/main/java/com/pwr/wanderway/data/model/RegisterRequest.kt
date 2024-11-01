package com.pwr.wanderway.data.model

data class RegisterRequest(
    val email: String,
    val username: String,
    val password1: String,
    val password2: String
)
