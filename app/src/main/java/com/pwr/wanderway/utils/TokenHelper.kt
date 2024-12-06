package com.pwr.wanderway.utils

import android.util.Base64
import com.pwr.wanderway.data.local.TokenManager
import com.pwr.wanderway.data.model.TokenResponse
import org.json.JSONObject

class TokenHelper(private val tokenManager: TokenManager) {

    suspend fun saveTokens(tokenResponse: TokenResponse) {
        val expiryTime = calculateExpiryTime(tokenResponse.access)
        tokenManager.saveAccessToken(tokenResponse.access, expiryTime)
    }

    private fun calculateExpiryTime(token: String): Long {
        try {
            // Split the JWT and decode the payload
            val parts = token.split(".")
            if (parts.size != 3) {
                throw IllegalArgumentException("Invalid JWT format")
            }

            val payload = String(
                Base64.decode(
                    parts[1],
                    Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP
                )
            )
            val json = JSONObject(payload)

            // Extract the `exp` claim and convert to milliseconds
            val expirySeconds = json.getLong("exp")
            return expirySeconds * 1000 // Convert to milliseconds
        } catch (e: Exception) {
            throw IllegalArgumentException("Failed to parse JWT token", e)
        }
    }
}
