package com.pwr.wanderway.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Destination.ROOT,
        startDestination = Destination.AUTHORIZED_GROUP
    ) {
        composable(route = Destination.UNAUTHORIZED_GROUP) {
            UnauthorizedWrapper(
                moveToAuthorized = {
                    navController.navigate(Destination.AUTHORIZED_GROUP)
                }
            )
        }
        composable(route = Destination.AUTHORIZED_GROUP) {
            AuthorizedWrapper(
                moveToUnauthorized = {
                    navController.navigate(Destination.UNAUTHORIZED_GROUP)
                }
            )
        }
    }
}


object Destination {
    const val ROOT = "Root"
    const val UNAUTHORIZED_GROUP = "UnauthorizedGroup"
    const val WELCOME_SCREEN = "WelcomeScreen"
    const val LOGIN_SCREEN = "LoginScreen"
    const val REGISTER_SCREEN = "RegisterScreen"
    const val ACTIVATE_ACCOUNT_SCREEN = "ActivateAccountScreen"

    const val AUTHORIZED_GROUP = "AuthorizedGroup"
    const val HOME_SCREEN = "HomeScreen"
    const val BUILD_YOUR_OWN_ROUTE_SCREEN = "BuildYourRouteScreen"
    const val PREFERENCES_SCREEN = "PreferencesScreen"
    const val ROUTE_CHOICE_SCREEN = "RouteChoiceScreen"
    const val ROUTE_DISPLAY_SCREEN = "RouteDisplayScreen"
    const val FORUM_SCREEN = "Forum"
    const val ACCOUNT_SETTINGS_SCREEN = "AccountSettings"
}