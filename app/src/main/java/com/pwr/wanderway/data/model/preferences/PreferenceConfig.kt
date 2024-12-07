package com.pwr.wanderway.data.model.preferences

data class PreferenceConfig(
    val category: RoutePreferenceCategory,
    val options: List<RoutePreferenceOption>,
    val defaultOption: RoutePreferenceOption
)