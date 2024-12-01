package com.pwr.wanderway.navigation

enum class Destination(val route: String) {
    ROOT("Root"),
    UNAUTHORIZED_GROUP("UnauthorizedGroup"),
    WELCOME_SCREEN("WelcomeScreen"),
    LOGIN_SCREEN("LoginScreen"),
    REGISTER_SCREEN("RegisterScreen"),
    ACTIVATE_ACCOUNT_SCREEN("ActivateAccountScreen"),
    AUTHORIZED_GROUP("AuthorizedGroup"),
    HOME_SCREEN("HomeScreen"),
    BUILD_YOUR_OWN_ROUTE_SCREEN("BuildYourRouteScreen"),
    PREFERENCES_SCREEN("PreferencesScreen"),
    ROUTE_CHOICE_SCREEN("RouteChoiceScreen"),
    ROUTE_DISPLAY_SCREEN("RouteDisplayScreen"),
    LOCATION_ADDITION_SCREEN("LocationAdditionScreen"),
    FORUM_SCREEN("Forum"),
    ACCOUNT_SETTINGS_SCREEN("AccountSettings");
}