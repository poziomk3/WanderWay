package com.pwr.wanderway.presentation.accountSettings.settings

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LanguageSettingsScreen(
    backNav: () -> Unit,
) {
    Text("Language settings")
    Button(onClick = backNav) { Text("Chuj") }
}