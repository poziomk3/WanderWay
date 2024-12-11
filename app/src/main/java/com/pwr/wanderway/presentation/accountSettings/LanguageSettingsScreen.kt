package com.pwr.wanderway.presentation.accountSettings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.utils.languages.Languages
import com.pwr.wanderway.utils.languages.Languages.Companion.getFlagByLocaleCode
import com.pwr.wanderway.utils.restartMainActivity

@Composable
fun LanguageSettingsScreen(
    settingsViewModel: SettingsViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val languages = Languages.entries
    val currentLocale = settingsViewModel.getCurrentLanguage()

    var selectedLocale by remember { mutableStateOf(currentLocale) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(languages) { language ->
                val locale = language.locale
                val isSelected = locale == selectedLocale
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedLocale = locale }
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isSelected,
                        onClick = { selectedLocale = locale }
                    )
                    Text(
                        text = getFlagByLocaleCode(language.localeCode),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = locale.displayLanguage,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
        WideButton(
            onClick = {
                settingsViewModel.changeLanguage(selectedLocale)
                showDialog = true
            },
            enabled = selectedLocale != currentLocale,
            text = stringResource(R.string.language_settings_screen_save),
            colorType = ButtonColor.PRIMARY,
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