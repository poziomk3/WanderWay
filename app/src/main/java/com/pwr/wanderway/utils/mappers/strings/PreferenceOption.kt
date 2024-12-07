package com.pwr.wanderway.utils.mappers.strings

import com.pwr.wanderway.R
import com.pwr.wanderway.data.model.preferences.RoutePreferenceOption

val routePreferenceOptionDisplayNames = mapOf(
    // Duration options
    RoutePreferenceOption.HALF_DAY to R.string.preference_option_half_day,
    RoutePreferenceOption.FULL_DAY to R.string.preference_option_full_day,
    RoutePreferenceOption.WEEKEND to R.string.preference_option_weekend,

    // Sightseeing options
    RoutePreferenceOption.HISTORICAL to R.string.preference_option_historical,
    RoutePreferenceOption.NATURE to R.string.preference_option_nature,
    RoutePreferenceOption.MODERN_ATTRACTIONS to R.string.preference_option_modern_attractions,

    // Group options
    RoutePreferenceOption.SOLO to R.string.preference_option_solo,
    RoutePreferenceOption.FAMILY to R.string.preference_option_family,
    RoutePreferenceOption.FRIENDS to R.string.preference_option_friends,
    RoutePreferenceOption.TOUR_GROUP to R.string.preference_option_tour_group,

    // Difficulty options
    RoutePreferenceOption.EASY to R.string.preference_option_easy,
    RoutePreferenceOption.MODERATE to R.string.preference_option_moderate,
    RoutePreferenceOption.CHALLENGING to R.string.preference_option_challenging,

    // Weather options
    RoutePreferenceOption.ANY to R.string.preference_option_any,
    RoutePreferenceOption.SUNNY to R.string.preference_option_sunny,
    RoutePreferenceOption.CLOUDY to R.string.preference_option_cloudy,
    RoutePreferenceOption.COOL to R.string.preference_option_cool,

    // Transport options
    RoutePreferenceOption.CAR to R.string.preference_option_car,
    RoutePreferenceOption.PUBLIC_TRANSPORT to R.string.preference_option_public_transport,
    RoutePreferenceOption.BIKE to R.string.preference_option_bike,
    RoutePreferenceOption.WALKING to R.string.preference_option_walking,

    // Meal options
    RoutePreferenceOption.QUICK_SNACKS to R.string.preference_option_quick_snacks,
    RoutePreferenceOption.LOCAL_CUISINE to R.string.preference_option_local_cuisine,
    RoutePreferenceOption.PACKED_LUNCH to R.string.preference_option_packed_lunch,

    // Budget options
    RoutePreferenceOption.LOW to R.string.preference_option_low,
    RoutePreferenceOption.MEDIUM to R.string.preference_option_medium,
    RoutePreferenceOption.HIGH to R.string.preference_option_high,

    // Activity options
    RoutePreferenceOption.RELAXING to R.string.preference_option_relaxing,
    RoutePreferenceOption.ADVENTUROUS to R.string.preference_option_adventurous,
    RoutePreferenceOption.EDUCATIONAL to R.string.preference_option_educational
)
