package com.pwr.wanderway.data.repository

import com.pwr.wanderway.data.local.RoutePreferencesManager
import com.pwr.wanderway.data.model.preferences.RoutePreferenceCategory
import com.pwr.wanderway.data.model.preferences.RoutePreferenceOption
import com.pwr.wanderway.data.model.preferences.preferenceConfigurations
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoutePreferencesRepository @Inject constructor(
    private val routePreferencesManager: RoutePreferencesManager
) {

    fun getActivePreferenceFlow(category: RoutePreferenceCategory): Flow<RoutePreferenceOption> {
        return routePreferencesManager.getPreferenceFlow(category.label).map { backendName ->
            RoutePreferenceOption.entries.find { it.label == backendName }
                ?: getDefaultOptionForCategory(category)
        }
    }

    suspend fun savePreference(category: RoutePreferenceCategory, selectedOption: RoutePreferenceOption) {
        routePreferencesManager.savePreference(category.label, selectedOption.label)
    }

    fun getDefaultOptionForCategory(category: RoutePreferenceCategory): RoutePreferenceOption {
        return preferenceConfigurations.find { it.category == category }?.defaultOption
            ?: throw IllegalArgumentException("No default option found for category: $category")
    }

    fun getAllActivePreferences(): Flow<Map<RoutePreferenceCategory, RoutePreferenceOption>> {
        val flows = preferenceConfigurations.associate { config ->
            config.category to getActivePreferenceFlow(config.category)
        }
        return combineFlows(flows)
    }

    private fun combineFlows(
        flows: Map<RoutePreferenceCategory, Flow<RoutePreferenceOption>>
    ): Flow<Map<RoutePreferenceCategory, RoutePreferenceOption>> {
        return flows.entries.fold(flowOf(emptyMap())) { acc, (key, flow) ->
            acc.combine(flow) { map, value ->
                map + (key to value)
            }
        }
    }
}
