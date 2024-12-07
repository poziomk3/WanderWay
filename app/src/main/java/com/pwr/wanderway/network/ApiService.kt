package com.pwr.wanderway.network

import com.pwr.wanderway.data.model.LoginRequest
import com.pwr.wanderway.data.model.RegisterRequest
import com.pwr.wanderway.data.model.TokenResponse
import com.pwr.wanderway.data.model.api.route.AllPOIsDTO
import com.pwr.wanderway.data.model.api.route.RouteGenerateDTO
import com.pwr.wanderway.data.model.api.route.RouteGenerateRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/auth/dj-rest-auth/registration/")
    suspend fun register(@Body registrationData: RegisterRequest):Response<RegisterRequest>

    @POST("/auth/token/")
    suspend fun login(@Body loginData: LoginRequest): Response<TokenResponse>

    @POST("/auth/token/refresh/")
    suspend fun refreshToken(@Body refreshData: Map<String, String>): Response<TokenResponse>

    @GET("/route/poi")
    suspend fun getAllPOIs(): Response<AllPOIsDTO>


    @POST("/route/generate")
    suspend fun generateRoute(@Body routeData: RouteGenerateRequest): Response<RouteGenerateDTO>


    @GET("/route/{id}")
    suspend fun getRouteById(@Path("id") id: Int): Response<ResponseBody>
}