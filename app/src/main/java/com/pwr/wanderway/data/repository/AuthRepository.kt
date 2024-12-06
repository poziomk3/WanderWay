package com.pwr.wanderway.data.repository

import android.util.Log
import com.pwr.wanderway.data.local.TokenManager
import com.pwr.wanderway.data.model.LoginRequest
import com.pwr.wanderway.data.model.RegisterRequest
import com.pwr.wanderway.data.model.TokenResponse
import com.pwr.wanderway.network.ApiErrorHandler
import com.pwr.wanderway.network.ApiService
import com.pwr.wanderway.utils.TokenHelper
import kotlinx.coroutines.flow.firstOrNull


class AuthRepository(
    private val tokenManager: TokenManager,
    private val apiService: ApiService
) {
    private val tokenHelper = TokenHelper(tokenManager)

    suspend fun register(registerRequest: RegisterRequest): Result<Unit> {
        return try {
            val response = apiService.register(registerRequest)

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Registration failed with server error."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(
        loginRequest: LoginRequest
    ): Result<TokenResponse> {
        return try {
            val response = apiService.login(loginRequest)
            if (response.isSuccessful) {
                response.body()?.let { tokenResponse ->
                    tokenHelper.saveTokens(tokenResponse)
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


    suspend fun logout() {
        tokenManager.clearTokens()
    }


    suspend fun hasToken(): Boolean {
        val accessToken = tokenManager.accessTokenFlow.firstOrNull()
        return !accessToken.isNullOrEmpty() && tokenManager.hasValidAccessToken()
    }


}



