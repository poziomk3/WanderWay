package com.pwr.wanderway.network

import com.pwr.wanderway.data.local.TokenManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val accessToken = runBlocking {
            if (tokenManager.hasValidAccessToken()) {
                tokenManager.accessTokenFlow.firstOrNull()
            } else {
                null // Token is invalid or not available
            }
        }

        accessToken?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        val response = chain.proceed(requestBuilder.build())

        if (response.code == 401) {
            println("401 Unauthorized: Access token might be invalid or expired.")
        }

        return response
    }
}
