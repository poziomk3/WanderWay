package com.pwr.wanderway.presentation.accountSettings.settings

import android.content.res.Resources
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import java.util.Locale
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.utils.restartMainActivity

@Composable
fun LanguageSettingsScreen(
    languageSettingsViewModel: LanguageSettingsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val availableLocales = Resources.getSystem().assets.locales.filter { localeCode ->
        Locale(localeCode).language in listOf("en", "pl")
    }.map { localeCode -> Locale(localeCode) }

    var initialLocale by remember { mutableStateOf(Locale(context.resources.configuration.locales[0].language)) }
    var selectedLocale by remember { mutableStateOf(initialLocale) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(availableLocales) { locale ->
                val isSelected = locale == selectedLocale
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedLocale = locale }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isSelected,
                        onClick = { selectedLocale = locale }
                    )
                    Text(
                        text = locale.getDisplayName(locale),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
        WideButton(
            onClick = {
                languageSettingsViewModel.saveLocale(selectedLocale)
                initialLocale = selectedLocale
                showDialog = true
            },
            text = stringResource(R.string.language_settings_screen_save),
            colorType = ButtonColor.PRIMARY,
            enabled = selectedLocale != initialLocale
        )
    }
    if (showDialog) {
        AlertDialog(
            icon = { Icon(Icons.Filled.Warning, contentDescription = "Warning") },
            title = {
                Text(stringResource(R.string.language_settings_screen_restart_required))
            },
            text = {
                Text(stringResource(R.string.language_settings_screen_restart_prompt))
            },
            onDismissRequest = {},
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    restartMainActivity(context)
                }) {
                    Text(stringResource(R.string.language_settings_screen_restart))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                }) {
                    Text(stringResource(R.string.language_settings_screen_no_thanks))
                }
            })
    }
}