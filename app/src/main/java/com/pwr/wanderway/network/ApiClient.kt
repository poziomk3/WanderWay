package com.pwr.wanderway.network

import com.pwr.wanderway.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BACKEND_URL = BuildConfig.BACKEND_URL

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BACKEND_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}