package com.pwr.wanderway.navigation.authorized

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.extended.composable
import com.pwr.wanderway.navigation.extended.navigateTo
import com.pwr.wanderway.presentation.accountSettings.LanguageSettingsScreen
import com.pwr.wanderway.presentation.accountSettings.SettingsScreen
import com.pwr.wanderway.presentation.accountSettings.ThemeSettingsScreen
import com.pwr.wanderway.presentation.forum.ForumAdditionScreen
import com.pwr.wanderway.presentation.forum.ForumHomeScreen
import com.pwr.wanderway.presentation.forum.ForumPostScreen
import com.pwr.wanderway.presentation.preferences.PreferencesScreen
import com.pwr.wanderway.presentation.routeCore.BuildYourRouteScreen
import com.pwr.wanderway.presentation.routeCore.HomeScreen
import com.pwr.wanderway.presentation.routeCore.LocationAdditionScreen
import com.pwr.wanderway.presentation.routeCore.RouteChoiceScreen
import com.pwr.wanderway.presentation.routeCore.RouteDisplayScreen
import com.pwr.wanderway.presentation.routeCore.RouteViewModel

@RequiresApi(Build.VERSION_CODES.O)
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
                    navController.navigate(
                        Destination.createRouteWithArgument(
                            Destination.FORUM_ADDITION_SCREEN,
                            routeId
                        )
                    )
                },
                routeViewModel =
                routeViewModel,
                routeId = routeId.toInt() // Pass the extracted routeId
            )
        }

        composable(Destination.FORUM_HOME_SCREEN) {
            ForumHomeScreen(
                forumPostNav = { id ->
                    navController.navigate(
                        Destination.createRouteWithArgument(
                            Destination.FORUM_POST_SCREEN,
                            id.toString()
                        )
                    )
                }
            )
        }

        composable(
            route = Destination.FORUM_ADDITION_SCREEN.route,
            arguments = listOf(navArgument("routeId") { type = NavType.StringType })
        ) { backStackEntry ->
            val routeId = backStackEntry.arguments?.getString("routeId") ?: ""
            ForumAdditionScreen(
                routeId = routeId.toInt(),
                forumHomeNav = {
                    navController.navigateTo(Destination.FORUM_HOME_SCREEN)
                }
            )
        }


        composable(
            route = Destination.FORUM_POST_SCREEN.route,
            arguments = listOf(navArgument("routeId") { type = NavType.StringType })
        ) { backStackEntry ->
            val routeId = backStackEntry.arguments?.getString("routeId") ?: ""
            ForumPostScreen(
                postId = routeId.toInt(),
                routeDisplayNav = { id ->
                    navController.navigate(
                        Destination.createRouteWithArgument(
                            Destination.ROUTE_DISPLAY_SCREEN,
                            id.toString()
                        )
                    )
                }
            )
        }




        composable(Destination.LANGUAGE_SETTINGS_SCREEN) {
            LanguageSettingsScreen()
        }
        composable(Destination.THEME_SETTINGS_SCREEN) {
            ThemeSettingsScreen()
        }
        composable(Destination.ACCOUNT_SETTINGS_SCREEN) {
            SettingsScreen(preferencesNav = {
                navController.navigateTo(Destination.PREFERENCES_SCREEN)
            }, logout = {
                moveToUnauthorized()
            }, languageSettingsNav = {
                navController.navigateTo(Destination.LANGUAGE_SETTINGS_SCREEN)
            }, themeSettingsNav = {
                navController.navigateTo(Destination.THEME_SETTINGS_SCREEN)
            }
            )
        }

    }
}


