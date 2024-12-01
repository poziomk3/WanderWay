package com.pwr.wanderway.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.accountSettings.settingsHome.SettingsHomeScreen
import com.pwr.wanderway.presentation.forum.forumHome.ForumHome
import com.pwr.wanderway.presentation.routeCore.buildYourRoute.BuildYourRouteScreen
import com.pwr.wanderway.presentation.routeCore.commons.RouteSurface
import com.pwr.wanderway.presentation.routeCore.home.HomeScreen
import com.pwr.wanderway.presentation.routeCore.preferences.PreferencesScreen
import com.pwr.wanderway.presentation.routeCore.routeChoice.RouteChoiceScreen
import com.pwr.wanderway.presentation.routeCore.routeDisplay.RouteDisplayScreen
import kotlinx.coroutines.flow.map

@Composable
fun AuthorizedNavGraph(navController: NavHostController, moveToUnauthorized: () -> Unit) {
    NavHost(
        navController = navController,
        route = Destination.AUTHORIZED_GROUP,
        startDestination = Destination.HOME_SCREEN
    ) {
        composable(route = Destination.HOME_SCREEN) {
            HomeScreen(
                onClick = {
                    navController.navigate(Destination.BUILD_YOUR_OWN_ROUTE_SCREEN)
                }
            )
        }
        composable(route = Destination.BUILD_YOUR_OWN_ROUTE_SCREEN) {
            BuildYourRouteScreen()
        }
        composable(route = Destination.FORUM_SCREEN) {
            ForumHome()
        }
        composable(route = Destination.ACCOUNT_SETTINGS_SCREEN) {
            SettingsHomeScreen()
        }
        composable(route = Destination.PREFERENCES_SCREEN) {
            PreferencesScreen()
        }
        composable(route = Destination.ROUTE_CHOICE_SCREEN) {
            RouteChoiceScreen()
        }
        composable(route = Destination.ROUTE_DISPLAY_SCREEN) {
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
    }.collectAsState(initial = Destination.AUTHORIZED_GROUP)

    Scaffold(
        bottomBar = { NavBar(navController) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, paddingValues.calculateBottomPadding())) {
            RouteSurface(
                title = stringResource(getTopBarTitle(activeRoute.value)),
                onGoBack = { navController.popBackStack() }
            ) {
                AuthorizedNavGraph(
                    navController = navController,
                    moveToUnauthorized = moveToUnauthorized
                )
            }
        }
    }
}


@Composable
fun getTopBarTitle(route: String?): Int {
    return when (route) {
        Destination.HOME_SCREEN -> R.string.top_bar_home
        Destination.BUILD_YOUR_OWN_ROUTE_SCREEN -> R.string.top_bar_build_your_route
        Destination.FORUM_SCREEN -> R.string.top_bar_forum
        Destination.ACCOUNT_SETTINGS_SCREEN -> R.string.top_bar_account_settings
        Destination.PREFERENCES_SCREEN -> R.string.top_bar_preferences
        Destination.ROUTE_CHOICE_SCREEN -> R.string.top_bar_route_choice
        Destination.ROUTE_DISPLAY_SCREEN -> R.string.top_bar_route_display
        else -> R.string.top_bar_default
    }
}