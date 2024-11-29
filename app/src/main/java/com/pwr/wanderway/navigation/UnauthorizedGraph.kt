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
        route = Destination.UNAUTHORIZED_GROUP,
        startDestination = Destination.WELCOME_SCREEN
    ) {
        composable(route = Destination.WELCOME_SCREEN) {
            WelcomeScreen(
                onLoginClick = {
                    navController.navigate(Destination.LOGIN_SCREEN)
                },
                onRegisterClick = {
                    navController.navigate(Destination.REGISTER_SCREEN)
                }
            )
        }
        composable(route = Destination.LOGIN_SCREEN) {
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
        composable(route = Destination.REGISTER_SCREEN) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Destination.ACTIVATE_ACCOUNT_SCREEN)
                },
                onGoBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Destination.ACTIVATE_ACCOUNT_SCREEN) {
            ActivateAccountScreen(
                onSuccess = {
                    navController.navigate(Destination.LOGIN_SCREEN)
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