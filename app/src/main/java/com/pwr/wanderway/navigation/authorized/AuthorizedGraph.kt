package com.pwr.wanderway.navigation.authorized

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.presentation.accountSettings.settingsHome.SettingsHomeScreen
import com.pwr.wanderway.presentation.forum.forumHome.ForumHome
import com.pwr.wanderway.presentation.routeCore.buildYourRoute.BuildYourRouteScreen
import com.pwr.wanderway.presentation.routeCore.home.HomeScreen
import com.pwr.wanderway.presentation.routeCore.preferences.PreferencesScreen
import com.pwr.wanderway.presentation.routeCore.routeChoice.RouteChoiceScreen
import com.pwr.wanderway.presentation.routeCore.routeDisplay.RouteDisplayScreen

@Composable
fun AuthorizedNavGraph(navController: NavHostController, moveToUnauthorized: () -> Unit) {
    NavHost(
        navController = navController,
        route = Destination.AUTHORIZED_GROUP.route,
        startDestination = Destination.HOME_SCREEN.route
    ) {
        composable(route = Destination.HOME_SCREEN.route) {
            HomeScreen(
                onClick = {
                    navController.navigate(Destination.BUILD_YOUR_OWN_ROUTE_SCREEN.route)
                }
            )
        }
        composable(route = Destination.BUILD_YOUR_OWN_ROUTE_SCREEN.route) {
            BuildYourRouteScreen()
        }
        composable(route = Destination.FORUM_SCREEN.route) {
            ForumHome()
        }
        composable(route = Destination.ACCOUNT_SETTINGS_SCREEN.route) {
            SettingsHomeScreen()
        }
        composable(route = Destination.PREFERENCES_SCREEN.route) {
            PreferencesScreen()
        }
        composable(route = Destination.ROUTE_CHOICE_SCREEN.route) {
            RouteChoiceScreen()
        }
        composable(route = Destination.ROUTE_DISPLAY_SCREEN.route) {
            RouteDisplayScreen()
        }
    }
}


