package com.pwr.wanderway.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BACKEND_URL = "http://10.0.2.2:8000"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Logs request and response bodies
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // Add the interceptor to the client
        .build()

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BACKEND_URL)
            .client(client) // Set the custom client with interceptor
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}