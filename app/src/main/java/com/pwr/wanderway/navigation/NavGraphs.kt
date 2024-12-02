package com.pwr.wanderway.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pwr.wanderway.navigation.authorized.AuthorizedWrapper
import com.pwr.wanderway.navigation.extended.composable
import com.pwr.wanderway.navigation.extended.navigateTo
import com.pwr.wanderway.navigation.unathorized.UnauthorizedWrapper


@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Destination.ROOT.route,
        startDestination = Destination.AUTHORIZED_GROUP.route
    ) {
        composable(Destination.UNAUTHORIZED_GROUP) {
            UnauthorizedWrapper(
                moveToAuthorized = {
                    navController.navigateTo(Destination.AUTHORIZED_GROUP)
                }
            )
        }
        composable(Destination.AUTHORIZED_GROUP) {
            AuthorizedWrapper(
                moveToUnauthorized = {
                    navController.navigateTo(Destination.UNAUTHORIZED_GROUP)
                }
            )
        }
    }
}




