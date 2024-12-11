package com.pwr.wanderway.presentation.accountSettings.settings

import androidx.lifecycle.ViewModel
import com.pwr.wanderway.data.local.SettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LanguageSettingsViewModel @Inject constructor(
    private val settingsManager: SettingsManager
): ViewModel() {
    fun saveLocale(locale: Locale) {
        runBlocking {
            settingsManager.saveSetting("selected_locale", locale.language)
        }
    }
}