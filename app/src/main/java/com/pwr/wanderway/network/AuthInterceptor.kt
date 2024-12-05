package com.pwr.wanderway.network

import com.pwr.wanderway.data.local.TokenManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // Fetch the access token (this might need to be run on a coroutine)
        val accessToken = runBlocking {
            tokenManager.accessTokenFlow.firstOrNull()
        }

        // If token is available, add it to the Authorization header
        accessToken?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}