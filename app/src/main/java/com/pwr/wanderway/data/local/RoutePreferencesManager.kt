package com.pwr.wanderway.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.userPreferencesDataStore by preferencesDataStore(name = "user_preferences")

class RoutePreferencesManager(private val context: Context) {

    // Save a preference using its backend name
    suspend fun savePreference(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.userPreferencesDataStore.edit { preferences ->
            preferences[preferenceKey] = value
        }
    }

    // Retrieve a preference as a flow
    fun getPreferenceFlow(key: String): Flow<String?> {
        val preferenceKey = stringPreferencesKey(key)
        return context.userPreferencesDataStore.data.map { preferences ->
            preferences[preferenceKey]
        }
    }

    // Clear all preferences (optional utility method)
    suspend fun clearPreferences() {
        context.userPreferencesDataStore.edit { preferences ->
            preferences.clear()
        }
    }
}