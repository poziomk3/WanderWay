package com.pwr.wanderway.navigation.authorized

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pwr.wanderway.navigation.Destination
import kotlinx.coroutines.flow.map

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