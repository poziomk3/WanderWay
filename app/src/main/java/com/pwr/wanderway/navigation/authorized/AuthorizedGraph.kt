package com.pwr.wanderway.navigation.authorized

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.extended.composable
import com.pwr.wanderway.navigation.extended.navigateTo
import com.pwr.wanderway.presentation.accountSettings.SettingsScreen
import com.pwr.wanderway.presentation.forum.ForumAdditionScreen
import com.pwr.wanderway.presentation.forum.ForumHomeScreen
import com.pwr.wanderway.presentation.preferences.PreferencesScreen
import com.pwr.wanderway.presentation.routeCore.BuildYourRouteScreen
import com.pwr.wanderway.presentation.routeCore.HomeScreen
import com.pwr.wanderway.presentation.routeCore.LocationAdditionScreen
import com.pwr.wanderway.presentation.routeCore.RouteChoiceScreen
import com.pwr.wanderway.presentation.routeCore.RouteDisplayScreen
import com.pwr.wanderway.presentation.routeCore.RouteViewModel

@Composable
fun AuthorizedNavGraph(
    navController: NavHostController,
    moveToUnauthorized: () -> Unit,
    routeViewModel: RouteViewModel,
) {
    NavHost(
        navController = navController,
        route = Destination.AUTHORIZED_GROUP.route,
        startDestination = Destination.HOME_SCREEN.route
    ) {


        composable(Destination.HOME_SCREEN) {
            HomeScreen(buildYourOwnRouteNav = {
                navController.navigateTo(Destination.BUILD_YOUR_OWN_ROUTE_SCREEN)
            })
        }
        composable(Destination.BUILD_YOUR_OWN_ROUTE_SCREEN) {
            BuildYourRouteScreen(locationAdditionNav = {
                navController.navigateTo(Destination.LOCATION_ADDITION_SCREEN)
            }, preferencesNav = {
                navController.navigateTo(Destination.PREFERENCES_SCREEN)
            }, routeChoiceNav = {
                navController.navigateTo(Destination.ROUTE_CHOICE_SCREEN)
            }, routeViewModel = routeViewModel
            )
        }
        composable(Destination.LOCATION_ADDITION_SCREEN) {
            LocationAdditionScreen(
                backNav = {
                    navController.popBackStack()
                }, routeViewModel = routeViewModel
            )
        }

        composable(Destination.ACCOUNT_SETTINGS_SCREEN) {
            SettingsScreen(preferencesNav = {
                navController.navigateTo(Destination.PREFERENCES_SCREEN)
            }, logout = {
                moveToUnauthorized()
            })
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
                routeDisplayNav = { id ->
                    navController.navigate(
                        Destination.createRouteWithArgument(
                            Destination.ROUTE_DISPLAY_SCREEN,
                            id.toString()
                        )
                    )
                }, routeViewModel = routeViewModel
            )
        }
        composable(
            route = Destination.ROUTE_DISPLAY_SCREEN.route,
            arguments = listOf(navArgument("routeId") { type = NavType.StringType })
        ) { backStackEntry ->
            val routeId = backStackEntry.arguments?.getString("routeId") ?: ""
            RouteDisplayScreen(
                buildYourOwnRouteNav = {
                    navController.navigateTo(Destination.BUILD_YOUR_OWN_ROUTE_SCREEN)
                },
                forumAdditionNav = {
                    navController.navigateTo(Destination.FORUM_ADDITION_SCREEN)
                },
                routeViewModel =
                routeViewModel,
                routeId = routeId // Pass the extracted routeId
            )
        }

        composable(Destination.FORUM_HOME_SCREEN) {
            ForumHomeScreen(
                forumAdditionNav = {
                    navController.navigateTo(Destination.FORUM_ADDITION_SCREEN)
                }
            )
        }
        composable(Destination.FORUM_ADDITION_SCREEN) {
            ForumAdditionScreen(
                forumHomeNav = {
                    navController.navigateTo(Destination.FORUM_HOME_SCREEN)
                }
            )
        }

    }
}


