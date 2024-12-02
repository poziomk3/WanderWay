package com.pwr.wanderway.data.model.preferences

enum class PreferenceOption(val backendName: String) {
    // Options for duration
    HALF_DAY("half_day"),
    FULL_DAY("full_day"),
    WEEKEND("weekend"),

    // Options for sightseeing
    HISTORICAL("historical"),
    NATURE("nature"),
    MODERN_ATTRACTIONS("modern_attractions"),

    // Options for group
    SOLO("solo"),
    FAMILY("family"),
    FRIENDS("friends"),
    TOUR_GROUP("tour_group"),

    // Options for difficulty
    EASY("easy"),
    MODERATE("moderate"),
    CHALLENGING("challenging"),

    // Options for weather
    ANY("any"),
    SUNNY("sunny"),
    CLOUDY("cloudy"),
    COOL("cool"),

    // Options for transport
    CAR("car"),
    PUBLIC_TRANSPORT("public_transport"),
    BIKE("bike"),
    WALKING("walking"),

    // Options for meal
    QUICK_SNACKS("quick_snacks"),
    LOCAL_CUISINE("local_cuisine"),
    PACKED_LUNCH("packed_lunch"),

    // Options for budget
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high"),

    // Options for activity
    RELAXING("relaxing"),
    ADVENTUROUS("adventurous"),
    EDUCATIONAL("educational")
}