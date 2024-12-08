package com.pwr.wanderway.data.model.preferences

data class PreferenceDropdownData(
    val category: RoutePreferenceCategory, // Links to the preference category
    val options: List<RoutePreferenceOption>, // List of preference options
    val defaultOption: RoutePreferenceOption // Default preference option
)