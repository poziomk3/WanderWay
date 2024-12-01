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
        route = Destination.UNAUTHORIZED_GROUP.route,
        startDestination = Destination.WELCOME_SCREEN.route
    ) {
        composable(route = Destination.WELCOME_SCREEN.route) {
            WelcomeScreen(
                onLoginClick = {
                    navController.navigate(Destination.LOGIN_SCREEN.route)
                },
                onRegisterClick = {
                    navController.navigate(Destination.REGISTER_SCREEN.route)
                }
            )
        }
        composable(route = Destination.LOGIN_SCREEN.route) {
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
        composable(route = Destination.REGISTER_SCREEN.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Destination.ACTIVATE_ACCOUNT_SCREEN.route)
                },
                onGoBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Destination.ACTIVATE_ACCOUNT_SCREEN.route) {
            ActivateAccountScreen(
                onSuccess = {
                    navController.navigate(Destination.LOGIN_SCREEN.route)
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