package com.pwr.wanderway.data.model.preferences

data class PreferenceConfig(
    val category: PreferenceCategory,
    val options: List<PreferenceOption>,
    val defaultOption: PreferenceOption
)