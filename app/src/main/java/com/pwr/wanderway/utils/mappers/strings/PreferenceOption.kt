package com.pwr.wanderway.utils.mappers.strings

import com.pwr.wanderway.R
import com.pwr.wanderway.data.model.preferences.PreferenceOption

val preferenceOptionDisplayNames = mapOf(
    // Duration options
    PreferenceOption.HALF_DAY to R.string.preference_option_half_day,
    PreferenceOption.FULL_DAY to R.string.preference_option_full_day,
    PreferenceOption.WEEKEND to R.string.preference_option_weekend,

    // Sightseeing options
    PreferenceOption.HISTORICAL to R.string.preference_option_historical,
    PreferenceOption.NATURE to R.string.preference_option_nature,
    PreferenceOption.MODERN_ATTRACTIONS to R.string.preference_option_modern_attractions,

    // Group options
    PreferenceOption.SOLO to R.string.preference_option_solo,
    PreferenceOption.FAMILY to R.string.preference_option_family,
    PreferenceOption.FRIENDS to R.string.preference_option_friends,
    PreferenceOption.TOUR_GROUP to R.string.preference_option_tour_group,

    // Difficulty options
    PreferenceOption.EASY to R.string.preference_option_easy,
    PreferenceOption.MODERATE to R.string.preference_option_moderate,
    PreferenceOption.CHALLENGING to R.string.preference_option_challenging,

    // Weather options
    PreferenceOption.ANY to R.string.preference_option_any,
    PreferenceOption.SUNNY to R.string.preference_option_sunny,
    PreferenceOption.CLOUDY to R.string.preference_option_cloudy,
    PreferenceOption.COOL to R.string.preference_option_cool,

    // Transport options
    PreferenceOption.CAR to R.string.preference_option_car,
    PreferenceOption.PUBLIC_TRANSPORT to R.string.preference_option_public_transport,
    PreferenceOption.BIKE to R.string.preference_option_bike,
    PreferenceOption.WALKING to R.string.preference_option_walking,

    // Meal options
    PreferenceOption.QUICK_SNACKS to R.string.preference_option_quick_snacks,
    PreferenceOption.LOCAL_CUISINE to R.string.preference_option_local_cuisine,
    PreferenceOption.PACKED_LUNCH to R.string.preference_option_packed_lunch,

    // Budget options
    PreferenceOption.LOW to R.string.preference_option_low,
    PreferenceOption.MEDIUM to R.string.preference_option_medium,
    PreferenceOption.HIGH to R.string.preference_option_high,

    // Activity options
    PreferenceOption.RELAXING to R.string.preference_option_relaxing,
    PreferenceOption.ADVENTUROUS to R.string.preference_option_adventurous,
    PreferenceOption.EDUCATIONAL to R.string.preference_option_educational
)
