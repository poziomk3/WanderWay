package com.pwr.wanderway.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Destination.ROOT.route,
        startDestination = Destination.AUTHORIZED_GROUP.route
    ) {
        composable(route = Destination.UNAUTHORIZED_GROUP.route) {
            UnauthorizedWrapper(
                moveToAuthorized = {
                    navController.navigate(Destination.AUTHORIZED_GROUP.route)
                }
            )
        }
        composable(route = Destination.AUTHORIZED_GROUP.route) {
            AuthorizedWrapper(
                moveToUnauthorized = {
                    navController.navigate(Destination.UNAUTHORIZED_GROUP.route)
                }
            )
        }
    }
}




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
    FORUM_SCREEN("Forum"),
    ACCOUNT_SETTINGS_SCREEN("AccountSettings");
}

inline fun <reified T : Enum<T>> mapStringToEnum(value: String?, propertySelector: (T) -> String): T? {
    return enumValues<T>().find { propertySelector(it) == value }
}
