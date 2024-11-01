package com.pwr.wanderway.data.model

data class RegisterResponse(
    val username: String,
    val email: String,
    val password1: String,
    val password2: String
)