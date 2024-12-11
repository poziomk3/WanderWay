package com.pwr.wanderway.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.settingsDataStore by preferencesDataStore(name = "user_settings")

class SettingsManager(private val context: Context) {

    // Save a setting using its backend name
    suspend fun saveSetting(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.settingsDataStore.edit { settings ->
            settings[preferenceKey] = value
        }
    }

    // Retrieve a setting as a flow
    fun getSettingFlow(key: String): Flow<String?> {
        val preferenceKey = stringPreferencesKey(key)
        return context.settingsDataStore.data.map { setting ->
            setting[preferenceKey]
        }
    }

}