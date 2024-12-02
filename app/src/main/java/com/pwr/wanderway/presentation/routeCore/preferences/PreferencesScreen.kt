package com.pwr.wanderway.presentation.routeCore.preferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.data.model.preferences.preferenceConfigurations
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.presentation.routeCore.commons.Dropdown
import com.pwr.wanderway.ui.theme.AppTheme
import com.pwr.wanderway.utils.mappers.getPreferenceOptionLabel
import com.pwr.wanderway.utils.mappers.mapPreferenceConfigToDropdownConfig


@Composable
fun PreferencesScreen(viewModel: PreferencesViewModel = hiltViewModel(), backNav: () -> Unit) {
    // Observe active preferences
    val activePreferences by viewModel.getAllActivePreferences()
        .collectAsState(initial = emptyMap())

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
                // Compute label-to-option mapping (safe for composable calls)
                val optionLabelMap = config.options.associateWith { getPreferenceOptionLabel(it) }

                // Determine the active preference or fallback to default
                val activeOption = activePreferences[config.category]?.name
                    ?: config.defaultOption.name

                // Get the currently selected item label
                val selectedItemLabel =
                    optionLabelMap[config.options.find { it.name == activeOption }]
                        ?: getPreferenceOptionLabel(config.defaultOption)

                Dropdown(
                    config = mapPreferenceConfigToDropdownConfig(config),
                    selectedItem = selectedItemLabel, // Pass the current selected item's label
                    onItemSelected = { selectedLabel ->
                        // Use the pre-mapped label-to-option relationship
                        val selectedOption =
                            optionLabelMap.entries.find { it.value == selectedLabel }?.key
                        if (selectedOption != null) {
                            viewModel.savePreference(config.category, selectedOption)
                        }
                    }
                )
            }
        }

        WideButton(
            text = stringResource(id = R.string.preferences_screen_button_1),
            onClick = { /* Save Action */ },
            colorType = ButtonColor.SECONDARY
        )
        WideButton(
            text = stringResource(id = R.string.preferences_screen_button_2),
            onClick = { backNav() },
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

