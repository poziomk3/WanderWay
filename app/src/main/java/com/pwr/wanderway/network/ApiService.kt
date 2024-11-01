package com.pwr.wanderway.network

import com.pwr.wanderway.data.model.RegisterResponse
import com.yourpackage.data.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/auth/dj-rest-auth/registration/")
    fun register(@Body registrationData: Map<String, String>):Response<RegisterResponse>

    @POST("/auth/token/")
    fun login(@Body loginData: Map<String, String>): Response<TokenResponse>

    @POST("/auth/token/refresh/")
    fun refreshToken(@Body refreshData: Map<String, String>): Response<TokenResponse>
}