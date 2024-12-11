package com.pwr.wanderway.presentation.accountSettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.data.repository.AuthRepository
import com.pwr.wanderway.data.repository.SettingsRepository
import com.pwr.wanderway.utils.themes.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val colorSchemeFlow = settingsRepository.getCurrentColorSchemeFlow()
    val currentThemeFlow = settingsRepository.getCurrentThemeFlow()
    fun getCurrentLanguage(): Locale {
        return settingsRepository.getCurrentLanguageBlocking()
    }

    fun getCurrentTheme(): Theme? {
        return settingsRepository.getCurrentThemeBlocking()
    }

    fun changeLanguage(locale: Locale) {
        viewModelScope.launch {
            settingsRepository.changeLanguage(locale)
        }
    }

    fun changeTheme(theme: Theme) {
        viewModelScope.launch {
            settingsRepository.changeTheme(theme)
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}
