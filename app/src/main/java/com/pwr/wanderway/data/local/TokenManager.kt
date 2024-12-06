package com.pwr.wanderway.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

val Context.authDataStore by preferencesDataStore(name = "auth_data_store")

class TokenManager(private val context: Context) {

    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val TOKEN_EXPIRY_TIME_KEY = longPreferencesKey("token_expiry_time")
    }

    suspend fun saveAccessToken(token: String, expiryTime: Long) {
        context.authDataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = token
            preferences[TOKEN_EXPIRY_TIME_KEY] = expiryTime
        }
    }

    val accessTokenFlow: Flow<String?> = context.authDataStore.data
        .map { preferences ->
            preferences[ACCESS_TOKEN_KEY]
        }

    val tokenExpiryTimeFlow: Flow<Long?> = context.authDataStore.data
        .map { preferences ->
            preferences[TOKEN_EXPIRY_TIME_KEY]
        }

    suspend fun hasValidAccessToken(): Boolean {
        val currentTime = System.currentTimeMillis()
        val expiryTime = context.authDataStore.data.map { preferences ->
            preferences[TOKEN_EXPIRY_TIME_KEY]
        }.firstOrNull()

        return expiryTime != null && currentTime < expiryTime
    }

    suspend fun clearTokens() {
        context.authDataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
