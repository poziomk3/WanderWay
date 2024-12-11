package com.pwr.wanderway.utils.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import com.pwr.wanderway.ui.theme.darkScheme
import com.pwr.wanderway.ui.theme.highContrastDarkColorScheme
import com.pwr.wanderway.ui.theme.highContrastLightColorScheme
import com.pwr.wanderway.ui.theme.lightScheme
import com.pwr.wanderway.ui.theme.mediumContrastDarkColorScheme
import com.pwr.wanderway.ui.theme.mediumContrastLightColorScheme

enum class Theme {
    LIGHT,
    DARK,
    MEDIUM_CONTRAST_LIGHT,
    HIGH_CONTRAST_LIGHT,
    MEDIUM_CONTRAST_DARK,
    HIGH_CONTRAST_DARK,
    SYSTEM
}

val mapThemeToColorScheme = mapOf(
    Theme.LIGHT to lightScheme,
    Theme.DARK to darkScheme,
    Theme.MEDIUM_CONTRAST_LIGHT to mediumContrastLightColorScheme,
    Theme.HIGH_CONTRAST_LIGHT to highContrastLightColorScheme,
    Theme.MEDIUM_CONTRAST_DARK to mediumContrastDarkColorScheme,
    Theme.HIGH_CONTRAST_DARK to highContrastDarkColorScheme,
)

@Composable
fun resolveSystemTheme(): ColorScheme {
    return if (isSystemInDarkTheme()) darkScheme else lightScheme
}