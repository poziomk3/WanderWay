package com.pwr.wanderway.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pwr.wanderway.navigation.authorized.AuthorizedWrapper
import com.pwr.wanderway.navigation.extended.composable
import com.pwr.wanderway.navigation.extended.navigateTo
import com.pwr.wanderway.navigation.unathorized.UnauthorizedWrapper
import com.pwr.wanderway.presentation.commons.Loader
import com.pwr.wanderway.presentation.entryScreens.AuthViewModel


@Composable
fun RootNavigationGraph(navController: NavHostController) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val isLoggedIn = remember { mutableStateOf<Boolean?>(null) }
    LaunchedEffect(Unit) {
        isLoggedIn.value = authViewModel.isLoggedIn()
    }

    if (isLoggedIn.value == null) Loader()
    else {
        val startDestination = if (isLoggedIn.value == true) {
            Destination.AUTHORIZED_GROUP.route
        } else {
            Destination.UNAUTHORIZED_GROUP.route
        }

        NavHost(
            navController = navController,
            route = Destination.ROOT.route,
            startDestination = startDestination
        ) {

            composable(Destination.UNAUTHORIZED_GROUP) {
                UnauthorizedWrapper(
                    moveToAuthorized = {
                        navController.navigateTo(Destination.AUTHORIZED_GROUP)
                    },
                    authViewModel = authViewModel

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
}




