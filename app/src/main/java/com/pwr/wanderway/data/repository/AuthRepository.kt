package com.pwr.wanderway.data.repository

import android.util.Log
import com.pwr.wanderway.data.local.TokenManager
import com.pwr.wanderway.data.model.RegisterResponse
import com.pwr.wanderway.network.ApiService
import com.yourpackage.data.model.TokenResponse
import kotlinx.coroutines.flow.firstOrNull
import retrofit2.Response

class AuthRepository(
    private val tokenManager: TokenManager,
    private val apiService: ApiService
) {

    // Function to register a user and store token on success
    data class registerRequest(
        val email: String,
        val username: String,
        val password1: String,
        val password2: String
    )

    suspend fun registerUser(
        email: String,
        username: String,
        password1: String,
        password2: String
    ): Result<RegisterResponse?> {
        return try {
            val registrationData = registerRequest(email, username, password1, password2)
            Log.i("AuthRepository", "Registering user: $registrationData")
            val response = apiService.register(registrationData)
            handleApiResponse(response)
        } catch (e: Exception) {
            Log.e("AuthRepository", "Exception during registration: ${e.message}", e)
            Result.failure(e)
        }
    }

    // Function to log in a user and store token on success
    data class LoginRequest(val username: String, val password: String)

    suspend fun loginUser(
        username: String,
        password: String
    ): Result<TokenResponse> {
        return try {
            val loginData = LoginRequest(username, password)
            val response = apiService.login(loginData)
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let { tokenResponse ->
                    tokenManager.saveAccessToken(tokenResponse.access, calculateExpiryTime())
                    tokenResponse.refresh?.let { tokenManager.saveRefreshToken(it) }
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

    private fun calculateExpiryTime(): Long {
        val oneHourMillis = 60 * 60 * 1000L // 1 hour in milliseconds
        return System.currentTimeMillis() + oneHourMillis
    }

    private fun <T> handleApiResponse(response: Response<T>): Result<T?> {
        return if (response.isSuccessful) {
            if (response.code() == 204) {
                // Handle no content scenario as a success with a null body
                Result.success(null)
            } else if (response.body() != null) {
                Result.success(response.body())
            } else {
                Result.failure(Exception("Unexpected empty response body"))
            }
        } else {
            Result.failure(Exception(ApiErrorHandler.handleResponseError(response)))
        }
    }
}


object ApiErrorHandler {
    fun handleResponseError(response: Response<*>): String {
        return response.errorBody()?.string()?.takeIf { it.isNotBlank() }
            ?: "An unknown error occurred"
    }
}