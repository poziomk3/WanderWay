package com.pwr.wanderway.navigation.authorized

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.overrides.composable
import com.pwr.wanderway.navigation.overrides.navigateTo
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
        route = Destination.AUTHORIZED_GROUP.route, // This is still `route` here but encapsulated in Destination.
        startDestination = Destination.HOME_SCREEN.route
    ) {
        composable(Destination.HOME_SCREEN) {
            HomeScreen(
                onClick = {
                    navController.navigateTo(Destination.BUILD_YOUR_OWN_ROUTE_SCREEN)
                }
            )
        }
        composable(Destination.BUILD_YOUR_OWN_ROUTE_SCREEN) {
            BuildYourRouteScreen()
        }
        composable(Destination.FORUM_SCREEN) {
            ForumHome()
        }
        composable(Destination.ACCOUNT_SETTINGS_SCREEN) {
            SettingsHomeScreen()
        }
        composable(Destination.PREFERENCES_SCREEN) {
            PreferencesScreen()
        }
        composable(Destination.ROUTE_CHOICE_SCREEN) {
            RouteChoiceScreen()
        }
        composable(Destination.ROUTE_DISPLAY_SCREEN) {
            RouteDisplayScreen()
        }
    }
}


