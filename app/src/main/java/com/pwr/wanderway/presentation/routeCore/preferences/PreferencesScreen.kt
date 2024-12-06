package com.pwr.wanderway.presentation.routeCore.preferences

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.data.model.preferences.preferenceConfigurations
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.coreViewModels.PreferencesViewModel
import com.pwr.wanderway.presentation.routeCore.commons.Dropdown
import com.pwr.wanderway.ui.theme.AppTheme
import com.pwr.wanderway.utils.mappers.getPreferenceOptionLabel
import com.pwr.wanderway.utils.mappers.mapPreferenceConfigToDropdownConfig


@Composable
fun PreferencesScreen(viewModel: PreferencesViewModel = hiltViewModel(), backNav: () -> Unit) {
    // Observe active preferences
    val activePreferences by viewModel.getAllActivePreferences()
        .collectAsState(initial = emptyMap())
    Log.d("PreferencesScreen", activePreferences.toString())

    var tempPreferences by remember { mutableStateOf(activePreferences) }


    LaunchedEffect(activePreferences) {
        tempPreferences = activePreferences // Reassign to active preferences
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
                // Compute label-to-option mapping
                val optionLabelMap = config.options.associateWith { getPreferenceOptionLabel(it) }
                // Determine the active preference or fallback to default
                val activeOption = tempPreferences[config.category]?.name
                    ?: config.defaultOption.name

                // Get the currently selected item label
                val selectedItemLabel =
                    optionLabelMap[config.options.find { it.name == activeOption }]
                        ?: "essa"

                Dropdown(
                    config = mapPreferenceConfigToDropdownConfig(config),
                    selectedItem = selectedItemLabel,
                    onItemSelected = { selectedLabel ->
                        // Update the temporary preferences state
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
            onClick = {
                // Clear temporary preferences (reset to initial state)
                tempPreferences = activePreferences
            },
            colorType = ButtonColor.SECONDARY
        )
        WideButton(
            text = stringResource(id = R.string.preferences_screen_button_2),
            onClick = {
                // Save temporary preferences to ViewModel
                tempPreferences.forEach { (category, option) ->
                    viewModel.savePreference(category, option)
                }
                backNav() // Navigate back
            },
            colorType = ButtonColor.PRIMARY
        )
    }
}

@Composable
@Preview
fun PreferencesScreenScreenPreview() {
    AppTheme {
        Surface {
            PreferencesScreen(backNav = { })
        }
    }
}

