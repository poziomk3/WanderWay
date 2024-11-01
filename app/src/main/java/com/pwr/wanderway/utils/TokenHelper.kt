package com.pwr.wanderway.utils

import com.pwr.wanderway.data.local.TokenManager
import com.yourpackage.data.model.TokenResponse



class TokenHelper(private val tokenManager: TokenManager) {
    suspend fun saveTokens(tokenResponse: TokenResponse, expiryTime: Long) {
        tokenManager.saveAccessToken(tokenResponse.access, expiryTime)
        tokenResponse.refresh?.let { tokenManager.saveRefreshToken(it) }
    }
}