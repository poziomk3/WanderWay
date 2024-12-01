package com.pwr.wanderway.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pwr.wanderway.presentation.accountSettings.settingsHome.SettingsHomeScreen
import com.pwr.wanderway.presentation.forum.forumHome.ForumHome
import com.pwr.wanderway.presentation.routeCore.buildYourRoute.BuildYourRouteScreen
import com.pwr.wanderway.presentation.routeCore.commons.TopBar
import com.pwr.wanderway.presentation.routeCore.home.HomeScreen
import com.pwr.wanderway.presentation.routeCore.preferences.PreferencesScreen
import com.pwr.wanderway.presentation.routeCore.routeChoice.RouteChoiceScreen
import com.pwr.wanderway.presentation.routeCore.routeDisplay.RouteDisplayScreen
import kotlinx.coroutines.flow.map

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


@Composable
fun AuthorizedWrapper(
    navController: NavHostController = rememberNavController(),
    moveToUnauthorized: () -> Unit
) {

    val activeRoute = remember(navController) {
        navController.currentBackStackEntryFlow
            .map { it.destination.route }
    }.collectAsState(initial = Destination.AUTHORIZED_GROUP.route)

    Scaffold(
        topBar = {
            TopBar(
                route = Destination.entries.find { it.route == activeRoute.value },
                onNavigationIconClick = {})
        },
        bottomBar = { NavBar(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(
                0.dp,
                paddingValues.calculateTopPadding(),
                0.dp,
                paddingValues.calculateBottomPadding()
            )
        ) {

            AuthorizedNavGraph(
                navController = navController,
                moveToUnauthorized = moveToUnauthorized
            )
        }
    }
}


