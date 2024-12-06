package com.pwr.wanderway.presentation.routeCore.preferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.coreViewModels.PreferencesViewModel
import com.pwr.wanderway.data.model.preferences.PreferenceCategory
import com.pwr.wanderway.data.model.preferences.PreferenceOption
import com.pwr.wanderway.data.model.preferences.preferenceConfigurations
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.presentation.routeCore.commons.Dropdown
import com.pwr.wanderway.ui.theme.AppTheme
import com.pwr.wanderway.utils.mappers.getPreferenceOptionLabel
import com.pwr.wanderway.utils.mappers.mapPreferenceConfigToDropdownConfig


@Composable
fun PreferencesScreen(preferencesViewModel: PreferencesViewModel, backNav: () -> Unit) {
    val loading by preferencesViewModel.loading.collectAsState()
    val activePreferences by preferencesViewModel.getAllActivePreferences().collectAsState()

    if (loading) {
        // Show a loading indicator while preferences are being loaded
        LoadingScreen()
    } else {
        // Show the preferences screen when loading is complete
        PreferencesContent(
            activePreferences = activePreferences,
            onSavePreferences = { preferences ->
                preferences.forEach { (category, option) ->
                    preferencesViewModel.savePreference(category, option)
                }
                backNav()
            },
            onResetPreferences = { preferencesViewModel.getAllActivePreferences().value }
        )
    }
}

@Composable
fun PreferencesContent(
    activePreferences: Map<PreferenceCategory, PreferenceOption>,
    onSavePreferences: (Map<PreferenceCategory, PreferenceOption>) -> Unit,
    onResetPreferences: () -> Unit
) {
    var tempPreferences by remember { mutableStateOf(activePreferences) }

    LaunchedEffect(activePreferences) {
        tempPreferences = activePreferences
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 8.dp, bottom = 6.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(preferenceConfigurations) { config ->
                val optionLabelMap = config.options.associateWith { getPreferenceOptionLabel(it) }
                val activeOption = tempPreferences[config.category]?.name
                    ?: config.defaultOption.name

                val selectedItemLabel =
                    optionLabelMap[config.options.find { it.name == activeOption }]
                        ?: "Unknown"

                Dropdown(
                    config = mapPreferenceConfigToDropdownConfig(config),
                    selectedItem = selectedItemLabel,
                    onItemSelected = { selectedLabel ->
                        val selectedOption =
                            optionLabelMap.entries.find { it.value == selectedLabel }?.key
                        if (selectedOption != null) {
                            tempPreferences = tempPreferences.toMutableMap().apply {
                                this[config.category] = selectedOption
                            }
                        }
                    }
                )
            }
        }

        WideButton(
            text = stringResource(id = R.string.preferences_screen_button_1),
            onClick = { onResetPreferences() },
            colorType = ButtonColor.SECONDARY
        )
        WideButton(
            text = stringResource(id = R.string.preferences_screen_button_2),
            onClick = { onSavePreferences(tempPreferences) },
            colorType = ButtonColor.PRIMARY
        )
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
@Preview
fun PreferencesScreenScreenPreview() {
    AppTheme {
        Surface {
            PreferencesScreen(backNav = { }, preferencesViewModel = hiltViewModel())
        }
    }
}

