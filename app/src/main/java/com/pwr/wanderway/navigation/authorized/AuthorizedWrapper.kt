package com.pwr.wanderway.navigation.authorized

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.extended.navigateTo
import com.pwr.wanderway.presentation.routeCore.RouteViewModel
import kotlinx.coroutines.flow.map

@Composable
fun AuthorizedWrapper(
    navController: NavHostController = rememberNavController(),
    moveToUnauthorized: () -> Unit
) {
    val routeViewModel: RouteViewModel = hiltViewModel()
    val entryRoutes = listOf(
        Destination.HOME_SCREEN,
        Destination.FORUM_HOME_SCREEN,
        Destination.ACCOUNT_SETTINGS_SCREEN
    )
    val activeDestination by remember(navController) {
        navController.currentBackStackEntryFlow
            .map { entry ->
                Destination.entries.find { it.route == entry.destination.route }
            }
    }.collectAsStateWithLifecycle(initialValue = Destination.AUTHORIZED_GROUP)

    Scaffold(
        topBar = {
            TopBar(
                route = activeDestination,
                onNavigationIconClick = if (activeDestination in entryRoutes) {
                    null
                } else {
                    { navController.popBackStack() }
                }
            )
        },
        bottomBar = {
            BottomBar(
                activeDestination = activeDestination ?: Destination.HOME_SCREEN,
                homeNav = { navController.navigateTo(Destination.HOME_SCREEN) },
                forumNav = { navController.navigateTo(Destination.FORUM_HOME_SCREEN) },
                accountNav = { navController.navigateTo(Destination.ACCOUNT_SETTINGS_SCREEN) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(
                    0.dp,
                    paddingValues.calculateTopPadding(),
                    0.dp,
                    paddingValues.calculateBottomPadding()
                )
                .fillMaxSize()
        ) {
            AuthorizedNavGraph(
                navController = navController,
                moveToUnauthorized = moveToUnauthorized,
                routeViewModel = routeViewModel,
            )
        }
    }
}
