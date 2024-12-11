package com.pwr.wanderway.network

import com.pwr.wanderway.data.model.LoginRequest
import com.pwr.wanderway.data.model.RegisterRequest
import com.pwr.wanderway.data.model.TokenResponse
import com.pwr.wanderway.data.model.api.forum.AllPostsDTO
import com.pwr.wanderway.data.model.api.forum.CreatedPost
import com.pwr.wanderway.data.model.api.forum.PublicPost
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
    suspend fun register(@Body registrationData: RegisterRequest): Response<RegisterRequest>

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

    @GET("/forum/getPosts")
    suspend fun getForumPosts(): Response<AllPostsDTO>

    @GET("forum/post/{postId}")
    suspend fun getForumPostById(
        @Path("postId") postId: Int,
    ): Response<PublicPost>

    @POST("/forum/post/{routeId}/")
    suspend fun addForumPost(
        @Path("routeId") routeId: Int,
        @Body post: CreatedPost,
    ): Response<ResponseBody>
}