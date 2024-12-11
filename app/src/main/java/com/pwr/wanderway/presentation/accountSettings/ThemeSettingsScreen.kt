package com.pwr.wanderway.presentation.accountSettings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.utils.mappers.strings.themeDisplayNames
import com.pwr.wanderway.utils.themes.Theme

@Composable
fun ThemeSettingsScreen(
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    darkTheme: Boolean = isSystemInDarkTheme(),
) {
    val currentTheme by settingsViewModel.currentThemeFlow.collectAsState(
        remember(settingsViewModel) { settingsViewModel.getCurrentTheme() }
    )
    val themes = Theme.entries


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(themes) { theme ->
                val isCurrentTheme = theme.toString() == currentTheme.toString()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { settingsViewModel.changeTheme(theme) }
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isCurrentTheme,
                        onClick = { settingsViewModel.changeTheme(theme) }
                    )
                    Text(
                        text = stringResource(themeDisplayNames[theme] ?: R.string.themes_light),
                        modifier = Modifier.padding(start = 8.dp)
                    )

                }
            }


        }

    }
}