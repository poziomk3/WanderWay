package com.pwr.wanderway.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.settingsDataStore by preferencesDataStore(name = "user_settings")

class SettingsManager(private val context: Context) {

    suspend fun saveSetting(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.settingsDataStore.edit { settings ->
            settings[preferenceKey] = value
        }
    }

    fun getSettingFlow(key: String): Flow<String?> {
        val preferenceKey = stringPreferencesKey(key)
        return context.settingsDataStore.data.map { setting ->
            setting[preferenceKey]
        }
    }

    suspend fun resetSettings() {
        context.settingsDataStore.edit { settings ->
            settings.clear()
        }
    }

}