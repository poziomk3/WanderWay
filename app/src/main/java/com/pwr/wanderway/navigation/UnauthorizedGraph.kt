package com.pwr.wanderway.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pwr.wanderway.presentation.entryScreens.activateAccount.ActivateAccountScreen
import com.pwr.wanderway.presentation.entryScreens.login.LoginScreen
import com.pwr.wanderway.presentation.entryScreens.register.RegisterScreen
import com.pwr.wanderway.presentation.entryScreens.welcome.WelcomeScreen

@Composable
fun UnauthorizedNavGraph(
    navController: NavHostController,
    moveToAuthorized: () -> Unit
) {
    NavHost(
        navController = navController,
        route = Destination.UnauthorizedGroup,
        startDestination = Destination.WelcomeScreen
    ) {
        composable(route = Destination.WelcomeScreen) {
            WelcomeScreen(
                onLoginClick = {
                    navController.navigate(Destination.LoginScreen)
                },
                onRegisterClick = {
                    navController.navigate(Destination.RegisterScreen)
                }
            )
        }
        composable(route = Destination.LoginScreen) {
            LoginScreen(
                onLoginSuccess = {
                    navController.popBackStack()
//                    navController.navigate(Destination.AuthorizedGroup) // Navigate to the main graph
                    moveToAuthorized()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Destination.RegisterScreen) {
            RegisterScreen(
                onRegisterSucess = {
                    navController.navigate(Destination.ActivateAccountScreen)
                },
                onGoBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Destination.ActivateAccountScreen) {
            ActivateAccountScreen(
                onSuccess = {
                    navController.navigate(Destination.LoginScreen)
                }
            )
        }
    }
}


@Composable
fun UnauthorizedWrapper(
    navController: NavHostController = rememberNavController(),
    moveToAuthorized: () -> Unit
) {
    UnauthorizedNavGraph(navController = navController, moveToAuthorized = moveToAuthorized)
}