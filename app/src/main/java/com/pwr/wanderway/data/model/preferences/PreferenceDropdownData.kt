package com.pwr.wanderway.data.model.preferences

data class PreferenceDropdownData(
    val category: PreferenceCategory, // Links to the preference category
    val options: List<PreferenceOption>, // List of preference options
    val defaultOption: PreferenceOption // Default preference option
)