package com.pwr.wanderway.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pwr.wanderway.presentation.accountSettings.settingsHome.SettingsHomeScreen
import com.pwr.wanderway.presentation.forum.forumHome.ForumHome
import com.pwr.wanderway.presentation.routeCore.buildYourRoute.BuildYourRouteScreen
import com.pwr.wanderway.presentation.routeCore.home.HomeScreen
import com.pwr.wanderway.presentation.routeCore.preferences.PreferencesScreen
import com.pwr.wanderway.presentation.routeCore.routeChoice.RouteChoiceScreen

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
        composable(route=Destination.ROUTE_CHOICE_SCREEN) {
            RouteChoiceScreen()
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthorizedWrapper(
    navController: NavHostController = rememberNavController(),
    moveToUnauthorized: () -> Unit
) {
    Scaffold(
        bottomBar = { NavBar(navController) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, paddingValues.calculateBottomPadding())) {
            AuthorizedNavGraph(
                navController = navController,
                moveToUnauthorized = moveToUnauthorized
            )
        }
    }
}