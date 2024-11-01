package com.pwr.wanderway.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "auth_data_store")

class TokenManager(private val context: Context) {

    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
        private val TOKEN_EXPIRY_TIME_KEY = longPreferencesKey("token_expiry_time")
    }

    suspend fun saveAccessToken(token: String, expiryTime: Long) {
        context.dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = token
            preferences[TOKEN_EXPIRY_TIME_KEY] = expiryTime
        }
    }

    val accessTokenFlow: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[ACCESS_TOKEN_KEY]
        }

    val tokenExpiryTimeFlow: Flow<Long?> = context.dataStore.data
        .map { preferences ->
            preferences[TOKEN_EXPIRY_TIME_KEY]
        }

    suspend fun saveRefreshToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[REFRESH_TOKEN_KEY] = token
        }
    }

    val refreshTokenFlow: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[REFRESH_TOKEN_KEY]
        }

    suspend fun hasValidAccessToken(): Boolean {
        val currentTime = System.currentTimeMillis()
        val expiryTime = context.dataStore.data.map { preferences ->
            preferences[TOKEN_EXPIRY_TIME_KEY]
        }.firstOrNull()

        return expiryTime != null && currentTime < expiryTime
    }

    suspend fun clearTokens() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
