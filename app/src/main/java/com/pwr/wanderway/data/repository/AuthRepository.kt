package com.pwr.wanderway.data.repository

import android.util.Log
import com.pwr.wanderway.data.local.TokenManager
import com.pwr.wanderway.data.model.LoginRequest
import com.pwr.wanderway.data.model.RegisterRequest
import com.pwr.wanderway.network.ApiService
import com.pwr.wanderway.utils.ApiResponseHandler
import com.pwr.wanderway.utils.TokenHelper
import com.yourpackage.data.model.TokenResponse
import kotlinx.coroutines.flow.firstOrNull
import retrofit2.Response


class AuthRepository(
    private val tokenManager: TokenManager,
    private val apiService: ApiService
) {
    private val tokenHelper = TokenHelper(tokenManager)


    suspend fun registerUser(
        email: String,
        username: String,
        password1: String,
        password2: String
    ): Result<RegisterRequest?> {
        return try {
            val registrationData = RegisterRequest(email, username, password1, password2)
            Log.i("AuthRepository", "Registering user: $registrationData")
            val response = apiService.register(registrationData)
            ApiResponseHandler.handleResponse(response)
        } catch (e: Exception) {
            Log.e("AuthRepository", "Exception during registration: ${e.message}", e)
            Result.failure(e)
        }
    }


    suspend fun loginUser(
        username: String,
        password: String
    ): Result<TokenResponse> {
        return try {
            val loginData = LoginRequest(username, password)
            val response = apiService.login(loginData)
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let { tokenResponse ->
                    tokenHelper.saveTokens(tokenResponse, calculateExpiryTime())
                }
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(ApiErrorHandler.handleResponseError(response)))
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Exception during login: ${e.message}", e)
            Result.failure(e)
        }
    }

    suspend fun ensureValidAccessToken(): Boolean {
        if (tokenManager.hasValidAccessToken()) {
            return true
        }

        val refreshToken = tokenManager.refreshTokenFlow.firstOrNull()
        return if (refreshToken != null) {
            val result = refreshUserToken(refreshToken)
            result.isSuccess
        } else {
            false
        }
    }

    suspend fun refreshUserToken(
        refresh: String
    ): Result<TokenResponse> {
        return try {
            val refreshData = mapOf("refresh" to refresh)
            val response = apiService.refreshToken(refreshData)

            if (response.isSuccessful && response.body() != null) {
                response.body()?.let { tokenResponse ->
                    tokenResponse.access?.let {
                        tokenManager.saveAccessToken(it, calculateExpiryTime())
                    }
                }
                Result.success(response.body()!!)
            } else {
                val errorMessage = ApiErrorHandler.handleResponseError(response)
                Result.failure(Exception("Token refresh failed: $errorMessage"))
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Exception during token refresh: ${e.message}", e)
            Result.failure(e)
        }
    }
    suspend fun logout() {
        tokenManager.clearTokens()
    }

    private fun calculateExpiryTime(): Long {
        val oneHourMillis = 60 * 60 * 1000L // 1 hour in milliseconds
        return System.currentTimeMillis() + oneHourMillis
    }
    suspend fun hasToken(): Boolean {
        val accessToken = tokenManager.accessTokenFlow.firstOrNull()
        return !accessToken.isNullOrEmpty() && tokenManager.hasValidAccessToken()
    }


}



object ApiErrorHandler {
    fun handleResponseError(response: Response<*>): String {
        return response.errorBody()?.string()?.takeIf { it.isNotBlank() }
            ?: "An unknown error occurred"
    }
}