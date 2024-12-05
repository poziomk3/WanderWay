package com.pwr.wanderway.navigation.unathorized

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.extended.composable
import com.pwr.wanderway.navigation.extended.navigateTo
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
        composable(Destination.WELCOME_SCREEN) {
            WelcomeScreen(
                onLoginClick = {
                    navController.navigateTo(Destination.LOGIN_SCREEN)
                },
                onRegisterClick = {
                    navController.navigateTo(Destination.REGISTER_SCREEN)
                },
                onAlreadyLoggedIn = {
                    moveToAuthorized() // Move to the authorized graph
                }
            )
        }
        composable(Destination.LOGIN_SCREEN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.popBackStack()
                    moveToAuthorized() // Move to the authorized graph
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(Destination.REGISTER_SCREEN) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigateTo(Destination.ACTIVATE_ACCOUNT_SCREEN)
                },
                onGoBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(Destination.ACTIVATE_ACCOUNT_SCREEN) {
            ActivateAccountScreen(
                onSuccess = {
                    navController.navigateTo(Destination.LOGIN_SCREEN)
                }
            )
        }
    }
}


