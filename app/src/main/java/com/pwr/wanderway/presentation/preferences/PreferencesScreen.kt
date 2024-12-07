package com.pwr.wanderway.presentation.preferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.data.model.preferences.PreferenceCategory
import com.pwr.wanderway.data.model.preferences.PreferenceOption
import com.pwr.wanderway.data.model.preferences.preferenceConfigurations
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.Loader
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.presentation.routeCore.composable.Dropdown
import com.pwr.wanderway.utils.mappers.getPreferenceOptionLabel
import com.pwr.wanderway.utils.mappers.mapPreferenceConfigToDropdownConfig


@Composable
fun PreferencesScreen(
    backNav: () -> Unit,
    routePreferencesViewModel: RoutePreferencesViewModel = hiltViewModel()
) {
    val loading by routePreferencesViewModel.loading.collectAsState()
    val activePreferences by routePreferencesViewModel.activePreferences.collectAsState()

    if (loading) {
        Loader()
    } else {
        PreferencesContent(
            activePreferences = activePreferences,
            onSavePreferences = { preferences ->
                preferences.forEach { (category, option) ->
                    routePreferencesViewModel.savePreference(category, option)
                }
                backNav()
            },
            onResetPreferences = {
                routePreferencesViewModel.resetPreferences()
            }
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


//@Composable
//@Preview
//fun PreferencesScreenScreenPreview() {
//    AppTheme {
//        Surface {
//            PreferencesScreen(backNav = { }, routePreferencesViewModel = hiltViewModel())
//        }
//    }
//}

