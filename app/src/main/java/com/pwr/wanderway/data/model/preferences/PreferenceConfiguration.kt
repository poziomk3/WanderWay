package com.pwr.wanderway.data.model.preferences

val preferenceConfigurations = listOf(
    PreferenceConfig(
        category = PreferenceCategory.DURATION,
        options = listOf(
            PreferenceOption.HALF_DAY,
            PreferenceOption.FULL_DAY,
            PreferenceOption.WEEKEND
        ),
        defaultOption = PreferenceOption.FULL_DAY
    ),
    PreferenceConfig(
        category = PreferenceCategory.SIGHTSEEING,
        options = listOf(
            PreferenceOption.HISTORICAL,
            PreferenceOption.NATURE,
            PreferenceOption.MODERN_ATTRACTIONS
        ),
        defaultOption = PreferenceOption.NATURE
    ),
    PreferenceConfig(
        category = PreferenceCategory.GROUP,
        options = listOf(
            PreferenceOption.SOLO,
            PreferenceOption.FAMILY,
            PreferenceOption.FRIENDS,
            PreferenceOption.TOUR_GROUP
        ),
        defaultOption = PreferenceOption.SOLO
    ),
    PreferenceConfig(
        category = PreferenceCategory.DIFFICULTY,
        options = listOf(
            PreferenceOption.EASY,
            PreferenceOption.MODERATE,
            PreferenceOption.CHALLENGING
        ),
        defaultOption = PreferenceOption.EASY
    ),
    PreferenceConfig(
        category = PreferenceCategory.WEATHER,
        options = listOf(
            PreferenceOption.ANY,
            PreferenceOption.SUNNY,
            PreferenceOption.CLOUDY,
            PreferenceOption.COOL
        ),
        defaultOption = PreferenceOption.ANY
    ),
    PreferenceConfig(
        category = PreferenceCategory.TRANSPORT,
        options = listOf(
            PreferenceOption.CAR,
            PreferenceOption.PUBLIC_TRANSPORT,
            PreferenceOption.BIKE,
            PreferenceOption.WALKING
        ),
        defaultOption = PreferenceOption.CAR
    ),
    PreferenceConfig(
        category = PreferenceCategory.MEAL,
        options = listOf(
            PreferenceOption.QUICK_SNACKS,
            PreferenceOption.LOCAL_CUISINE,
            PreferenceOption.PACKED_LUNCH
        ),
        defaultOption = PreferenceOption.QUICK_SNACKS
    ),
    PreferenceConfig(
        category = PreferenceCategory.BUDGET,
        options = listOf(PreferenceOption.LOW, PreferenceOption.MEDIUM, PreferenceOption.HIGH),
        defaultOption = PreferenceOption.MEDIUM
    ),
    PreferenceConfig(
        category = PreferenceCategory.ACTIVITY,
        options = listOf(
            PreferenceOption.RELAXING,
            PreferenceOption.ADVENTUROUS,
            PreferenceOption.EDUCATIONAL
        ),
        defaultOption = PreferenceOption.RELAXING
    )
)