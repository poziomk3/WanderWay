package com.pwr.wanderway.utils.mappers

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.pwr.wanderway.data.model.preferences.RoutePreferenceCategory
import com.pwr.wanderway.data.model.preferences.PreferenceConfig
import com.pwr.wanderway.data.model.preferences.RoutePreferenceOption
import com.pwr.wanderway.presentation.routeCore.composable.DropdownConfig
import com.pwr.wanderway.utils.mappers.strings.preferenceCategoryDisplayNames
import com.pwr.wanderway.utils.mappers.strings.routePreferenceOptionDisplayNames


@Composable
fun getPreferenceCategoryLabel(category: RoutePreferenceCategory): String {
    val resId = preferenceCategoryDisplayNames[category]
        ?: throw IllegalArgumentException("No label found for category: $category")
    return stringResource(resId)
}


@Composable
fun getPreferenceOptionLabel(option: RoutePreferenceOption): String {
    val resId = routePreferenceOptionDisplayNames[option]
        ?: throw IllegalArgumentException("No label found for option: $option")
    return stringResource(resId)
}



@Composable
fun mapPreferenceConfigToDropdownConfig(
    preferenceConfig: PreferenceConfig
): DropdownConfig {
    return DropdownConfig(
        label = getPreferenceCategoryLabel(preferenceConfig.category), // Map category to its label
        options = preferenceConfig.options.map { getPreferenceOptionLabel(it) }, // Map options to their labels
        defaultOption = getPreferenceOptionLabel(preferenceConfig.defaultOption) // Map default option to its label
    )
}