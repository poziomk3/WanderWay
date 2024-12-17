package com.pwr.wanderway.navigation.unathorized

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.extended.composable
import com.pwr.wanderway.navigation.extended.navigateTo
import com.pwr.wanderway.presentation.entryScreens.AuthViewModel
import com.pwr.wanderway.presentation.entryScreens.activateAccount.ActivateAccountScreen
import com.pwr.wanderway.presentation.entryScreens.login.LoginScreen
import com.pwr.wanderway.presentation.entryScreens.register.RegisterScreen
import com.pwr.wanderway.presentation.entryScreens.welcome.WelcomeScreen

@Composable
fun UnauthorizedNavGraph(
    navController: NavHostController,
    moveToAuthorized: () -> Unit,
    authViewModel: AuthViewModel
) {

    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { _, _, _ ->
            authViewModel.resetState()
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

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
            )
        }
        composable(Destination.LOGIN_SCREEN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.popBackStack()
                    moveToAuthorized() // Move to the authorized graph
                },
                onBackClick = {
                    navController.navigateTo(Destination.WELCOME_SCREEN)
                },
                authViewModel = authViewModel
            )
        }
        composable(Destination.REGISTER_SCREEN) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigateTo(Destination.ACTIVATE_ACCOUNT_SCREEN)
                },
                onGoBackClick = {
                    navController.popBackStack()
                },
                authViewModel = authViewModel
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


