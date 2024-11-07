package com.pwr.wanderway.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Destination.Root,
        startDestination = Destination.UnauthorizedGroup
    ) {
        composable(route = Destination.UnauthorizedGroup) {
            UnauthorizedWrapper(
                moveToAuthorized = {
                    navController.navigate(Destination.AuthorizedGroup)
                }
            )
        }
        composable(route = Destination.AuthorizedGroup) {
            AuthorizedWrapper(
                moveToUnauthorized = {
                    navController.navigate(Destination.UnauthorizedGroup)
                }
            )
        }
    }
}


object Destination {
    const val Root = "Root"
    const val UnauthorizedGroup = "UnauthorizedGroup"
    const val WelcomeScreen = "WelcomeScreen"
    const val LoginScreen = "LoginScreen"
    const val RegisterScreen = "RegisterScreen"
    const val ActivateAccountScreen = "ActivateAccountScreen"

    const val AuthorizedGroup = "AuthorizedGroup"
    const val HomeScreen = "HomeScreen"
    const val BuildYourRouteScreen = "BuildYourRouteScreen"
    const val Forum = "Forum"
    const val AccountSettings = "AccountSettings"
}