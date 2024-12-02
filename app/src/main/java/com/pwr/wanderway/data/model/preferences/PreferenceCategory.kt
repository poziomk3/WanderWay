package com.pwr.wanderway.data.model.preferences;


enum class PreferenceCategory(val backendName: String) {
    DURATION("duration"),
    SIGHTSEEING("sightseeing"),
    GROUP("group"),
    DIFFICULTY("difficulty"),
    WEATHER("weather"),
    TRANSPORT("transport"),
    MEAL("meal"),
    BUDGET("budget"),
    ACTIVITY("activity")
}