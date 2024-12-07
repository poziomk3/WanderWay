package com.pwr.wanderway.data.model.preferences

val preferenceConfigurations = listOf(
    PreferenceConfig(
        category = RoutePreferenceCategory.DURATION,
        options = listOf(
            RoutePreferenceOption.HALF_DAY,
            RoutePreferenceOption.FULL_DAY,
            RoutePreferenceOption.WEEKEND
        ),
        defaultOption = RoutePreferenceOption.FULL_DAY
    ),
    PreferenceConfig(
        category = RoutePreferenceCategory.SIGHTSEEING,
        options = listOf(
            RoutePreferenceOption.HISTORICAL,
            RoutePreferenceOption.NATURE,
            RoutePreferenceOption.MODERN_ATTRACTIONS
        ),
        defaultOption = RoutePreferenceOption.NATURE
    ),
    PreferenceConfig(
        category = RoutePreferenceCategory.GROUP,
        options = listOf(
            RoutePreferenceOption.SOLO,
            RoutePreferenceOption.FAMILY,
            RoutePreferenceOption.FRIENDS,
            RoutePreferenceOption.TOUR_GROUP
        ),
        defaultOption = RoutePreferenceOption.SOLO
    ),
    PreferenceConfig(
        category = RoutePreferenceCategory.DIFFICULTY,
        options = listOf(
            RoutePreferenceOption.EASY,
            RoutePreferenceOption.MODERATE,
            RoutePreferenceOption.CHALLENGING
        ),
        defaultOption = RoutePreferenceOption.EASY
    ),
    PreferenceConfig(
        category = RoutePreferenceCategory.WEATHER,
        options = listOf(
            RoutePreferenceOption.ANY,
            RoutePreferenceOption.SUNNY,
            RoutePreferenceOption.CLOUDY,
            RoutePreferenceOption.COOL
        ),
        defaultOption = RoutePreferenceOption.ANY
    ),
    PreferenceConfig(
        category = RoutePreferenceCategory.TRANSPORT,
        options = listOf(
            RoutePreferenceOption.CAR,
            RoutePreferenceOption.PUBLIC_TRANSPORT,
            RoutePreferenceOption.BIKE,
            RoutePreferenceOption.WALKING
        ),
        defaultOption = RoutePreferenceOption.CAR
    ),
    PreferenceConfig(
        category = RoutePreferenceCategory.MEAL,
        options = listOf(
            RoutePreferenceOption.QUICK_SNACKS,
            RoutePreferenceOption.LOCAL_CUISINE,
            RoutePreferenceOption.PACKED_LUNCH
        ),
        defaultOption = RoutePreferenceOption.QUICK_SNACKS
    ),
    PreferenceConfig(
        category = RoutePreferenceCategory.BUDGET,
        options = listOf(RoutePreferenceOption.LOW, RoutePreferenceOption.MEDIUM, RoutePreferenceOption.HIGH),
        defaultOption = RoutePreferenceOption.MEDIUM
    ),
    PreferenceConfig(
        category = RoutePreferenceCategory.ACTIVITY,
        options = listOf(
            RoutePreferenceOption.RELAXING,
            RoutePreferenceOption.ADVENTUROUS,
            RoutePreferenceOption.EDUCATIONAL
        ),
        defaultOption = RoutePreferenceOption.RELAXING
    )
)