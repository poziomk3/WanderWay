package com.pwr.wanderway.utils.mappers

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.pwr.wanderway.data.model.DropdownConfig
import com.pwr.wanderway.data.model.preferences.PreferenceCategory
import com.pwr.wanderway.data.model.preferences.PreferenceConfig
import com.pwr.wanderway.data.model.preferences.PreferenceOption


@Composable
fun getPreferenceCategoryLabel(category: PreferenceCategory): String {
    val resId = preferenceCategoryDisplayNames[category]
        ?: throw IllegalArgumentException("No label found for category: $category")
    return stringResource(resId)
}


@Composable
fun getPreferenceOptionLabel(option: PreferenceOption): String {
    val resId = preferenceOptionDisplayNames[option]
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