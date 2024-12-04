package com.pwr.wanderway.navigation.authorized

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.extended.composable
import com.pwr.wanderway.navigation.extended.navigateTo
import com.pwr.wanderway.presentation.accountSettings.SettingsScreen
import com.pwr.wanderway.presentation.forum.forumHome.ForumHome
import com.pwr.wanderway.presentation.routeCore.buildYourRoute.BuildYourRouteScreen
import com.pwr.wanderway.presentation.routeCore.home.HomeScreen
import com.pwr.wanderway.presentation.routeCore.locationAddition.LocationAdditionScreen
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
                buildYourOwnRouteNav = {
                    navController.navigateTo(Destination.BUILD_YOUR_OWN_ROUTE_SCREEN)
                }
            )
        }
        composable(Destination.BUILD_YOUR_OWN_ROUTE_SCREEN) {
            BuildYourRouteScreen(
                locationAdditionNav = {
                    navController.navigateTo(Destination.LOCATION_ADDITION_SCREEN)
                },
                preferencesNav = {
                    navController.navigateTo(Destination.PREFERENCES_SCREEN)
                },
                routeChoiceNav = {
                    navController.navigateTo(Destination.ROUTE_CHOICE_SCREEN)
                }
            )
        }
        composable(Destination.LOCATION_ADDITION_SCREEN) {
            LocationAdditionScreen(
                backNav = {
                    navController.popBackStack()
                }
            )
        }
        composable(Destination.FORUM_SCREEN) {
            ForumHome()
        }
        composable(Destination.ACCOUNT_SETTINGS_SCREEN) {
            SettingsScreen(
                preferencesNav = {
                    navController.navigateTo(Destination.PREFERENCES_SCREEN)
                }
            )
        }
        composable(Destination.PREFERENCES_SCREEN) {
            PreferencesScreen(
                backNav = {
                    navController.popBackStack()
                }
            )
        }
        composable(Destination.ROUTE_CHOICE_SCREEN) {
            RouteChoiceScreen(
                routeDisplayNav = {
                    navController.navigateTo(Destination.ROUTE_DISPLAY_SCREEN)
                }
            )
        }
        composable(Destination.ROUTE_DISPLAY_SCREEN) {
            RouteDisplayScreen(
                buildYourOwnRouteNav = {
                    navController.navigateTo(Destination.BUILD_YOUR_OWN_ROUTE_SCREEN)
                }, locationAdditionNav = {
                    navController.navigateTo(Destination.LOCATION_ADDITION_SCREEN)
                }
            )
        }

    }
}


