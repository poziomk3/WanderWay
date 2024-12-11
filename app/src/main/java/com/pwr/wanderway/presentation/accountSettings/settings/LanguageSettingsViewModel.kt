package com.pwr.wanderway.presentation.accountSettings.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import com.pwr.wanderway.data.local.SettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LanguageSettingsViewModel @Inject constructor(
    private val settingsManager: SettingsManager
) : ViewModel() {
    fun saveLocale(locale: Locale) {
        runBlocking {
            settingsManager.saveSetting("selected_locale", locale.language)
        }
    }


    fun getCurrentLocale(context: Context): Locale {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            context.resources.configuration.locales[0] // For Android N and above
        } else {
            @Suppress("DEPRECATION")
            context.resources.configuration.locale // For older versions
        }
    }

}