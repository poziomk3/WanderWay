package com.pwr.wanderway.data.repository

import com.pwr.wanderway.data.local.RoutePreferencesManager
import com.pwr.wanderway.data.model.preferences.PreferenceCategory
import com.pwr.wanderway.data.model.preferences.PreferenceOption
import com.pwr.wanderway.data.model.preferences.preferenceConfigurations
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoutePreferencesRepository @Inject constructor(
    private val routePreferencesManager: RoutePreferencesManager
) {

    fun getActivePreferenceFlow(category: PreferenceCategory): Flow<PreferenceOption> {
        return routePreferencesManager.getPreferenceFlow(category.backendName).map { backendName ->
            PreferenceOption.entries.find { it.backendName == backendName }
                ?: getDefaultOptionForCategory(category)
        }
    }

    suspend fun savePreference(category: PreferenceCategory, selectedOption: PreferenceOption) {
        routePreferencesManager.savePreference(category.backendName, selectedOption.backendName)
    }

    fun getDefaultOptionForCategory(category: PreferenceCategory): PreferenceOption {
        return preferenceConfigurations.find { it.category == category }?.defaultOption
            ?: throw IllegalArgumentException("No default option found for category: $category")
    }

    fun getAllActivePreferences(): Flow<Map<PreferenceCategory, PreferenceOption>> {
        val flows = preferenceConfigurations.associate { config ->
            config.category to getActivePreferenceFlow(config.category)
        }
        return combineFlows(flows)
    }

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
