package com.pwr.wanderway.network

import com.pwr.wanderway.data.model.RegisterResponse
import com.pwr.wanderway.data.repository.AuthRepository
import com.yourpackage.data.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/auth/dj-rest-auth/registration/")
    suspend fun register(@Body registrationData: AuthRepository.registerRequest):Response<RegisterResponse>

    @POST("/auth/token/")
    suspend fun login(@Body loginData: AuthRepository.LoginRequest): Response<TokenResponse>

    @POST("/auth/token/refresh/")
    suspend fun refreshToken(@Body refreshData: Map<String, String>): Response<TokenResponse>
}