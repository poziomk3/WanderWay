package com.pwr.wanderway.presentation.routeCore.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.data.local.PreferencesManager
import com.pwr.wanderway.data.model.preferences.PreferenceCategory
import com.pwr.wanderway.data.model.preferences.PreferenceOption
import com.pwr.wanderway.data.model.preferences.preferenceConfigurations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val dataStore: PreferencesManager
) : ViewModel() {

    // Get the current selected option for a specific category
    private fun getActivePreferenceFlow(category: PreferenceCategory): Flow<PreferenceOption> {
        return dataStore.getPreferenceFlow(category.backendName).map { backendName ->
            PreferenceOption.entries.find { it.backendName == backendName }
                ?: getDefaultOptionForCategory(category)
        }
    }

    // Save the selected option for a specific category
    fun savePreference(category: PreferenceCategory, selectedOption: PreferenceOption) {
        viewModelScope.launch {
            dataStore.savePreference(category.backendName, selectedOption.backendName)
        }
    }

    // Get default option for a category
    private fun getDefaultOptionForCategory(category: PreferenceCategory): PreferenceOption {
        return preferenceConfigurations.find { it.category == category }?.defaultOption
            ?: throw IllegalArgumentException("No default option found for category: $category")
    }

    // Get all active preferences as a map
    fun getAllActivePreferences(): Flow<Map<PreferenceCategory, PreferenceOption>> {
        val flows = preferenceConfigurations.associate { config ->
            config.category to getActivePreferenceFlow(config.category)
        }

        return combineFlows(flows)
    }

    // Combine multiple flows into a single map
    private fun combineFlows(
        flows: Map<PreferenceCategory, Flow<PreferenceOption>>
    ): Flow<Map<PreferenceCategory, PreferenceOption>> {
        return flows.entries.fold(flowOf(emptyMap())) { acc, (key, flow) ->
            acc.combine(flow) { map, value ->
                map + (key to value)
            }
        }
    }
}