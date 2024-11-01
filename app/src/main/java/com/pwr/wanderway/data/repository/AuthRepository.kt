package com.pwr.wanderway.data.repository

import com.pwr.wanderway.data.local.TokenManager
import com.pwr.wanderway.data.model.RegisterResponse
import com.pwr.wanderway.network.ApiClient
import com.yourpackage.data.model.TokenResponse

class AuthRepository(private val tokenManager: TokenManager) {
    private val apiService = ApiClient.apiService

    // Function to register a user and store token on success
    suspend fun registerUser(
        username: String,
        email: String,
        password1: String,
        password2: String
    ): Result<RegisterResponse> {
        return try {
            val registrationData = mapOf(
                "username" to username,
                "email" to email,
                "password1" to password1,
                "password2" to password2
            )
            val response = apiService.register(registrationData)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                Result.failure(Exception("Registration failed: $errorMessage"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Function to log in a user and store token on success
    suspend fun loginUser(
        username: String,
        password: String
    ): Result<TokenResponse> {
        return try {
            val loginData = mapOf(
                "username" to username,
                "password" to password
            )
            val response = apiService.login(loginData)

            if (response.isSuccessful && response.body() != null) {
                val tokenResponse = response.body()!!
                tokenResponse.access?.let { tokenManager.saveAccessToken(it) }
                tokenResponse.refresh?.let { tokenManager.saveRefreshToken(it) }
                Result.success(tokenResponse)
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                Result.failure(Exception("Login failed: $errorMessage"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Function to refresh a user token and update the stored token
    suspend fun refreshUserToken(
        refresh: String
    ): Result<TokenResponse> {
        return try {
            val refreshData = mapOf(
                "refresh" to refresh
            )
            val response = apiService.refreshToken(refreshData)

            if (response.isSuccessful && response.body() != null) {
                val tokenResponse = response.body()!!
                tokenResponse.access?.let { tokenManager.saveAccessToken(it) }
                Result.success(tokenResponse)
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                Result.failure(Exception("Token refresh failed: $errorMessage"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
