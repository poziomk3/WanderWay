package com.pwr.wanderway.data.repository

import com.pwr.wanderway.data.local.SettingsManager
import com.pwr.wanderway.ui.theme.lightScheme
import com.pwr.wanderway.utils.themes.Theme
import com.pwr.wanderway.utils.themes.mapThemeToColorScheme
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.util.Locale
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val settingsManager: SettingsManager
) {
    private val LANGUAGE = "language"
    private val THEME = "theme"

    suspend fun changeLanguage(language: Locale) {
        settingsManager.saveSetting(LANGUAGE, language.toLanguageTag())
    }

    fun getCurrentLanguageBlocking(): Locale {
        val languageTag = runBlocking {
            settingsManager.getSettingFlow(LANGUAGE).firstOrNull()
        }
        return if (languageTag != null) {
            Locale.forLanguageTag(languageTag)
        } else {
            Locale.getDefault()
        }
    }

    fun getCurrentThemeBlocking(): Theme {
        val themeName = runBlocking {
            settingsManager.getSettingFlow(THEME).firstOrNull()
        }
        return Theme.entries.find { it.name == themeName } ?: Theme.LIGHT
    }


    suspend fun changeTheme(theme: Theme) {
        settingsManager.saveSetting(THEME, theme.name)
    }


    fun getCurrentThemeFlow() =
        settingsManager.getSettingFlow(THEME)

    fun getCurrentColorSchemeFlow() =
        settingsManager.getSettingFlow(THEME)
            .map { themeName ->
                val theme = Theme.entries.find { it.name == themeName } ?: Theme.LIGHT
                mapThemeToColorScheme[theme] ?: lightScheme
            }

}
