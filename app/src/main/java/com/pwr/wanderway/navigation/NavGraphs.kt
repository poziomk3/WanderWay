package com.pwr.wanderway.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.pwr.wanderway.presentation.entryScreens.activateAccount.ActivateAccountScreen
import com.pwr.wanderway.presentation.entryScreens.activateAccount.ActivateAccountViewModel
import com.pwr.wanderway.presentation.entryScreens.login.LoginScreen
import com.pwr.wanderway.presentation.entryScreens.login.LoginViewModel
import com.pwr.wanderway.presentation.entryScreens.register.RegisterScreen
import com.pwr.wanderway.presentation.entryScreens.register.RegisterViewModel
import com.pwr.wanderway.presentation.entryScreens.welcome.WelcomeScreen
import com.pwr.wanderway.presentation.entryScreens.welcome.WelcomeViewModel
import com.pwr.wanderway.presentation.routeCore.home.HomeScreen
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.authorizedNavGraph() {
    navigation<Destination.AuthorizedGroup>(startDestination = Destination.HomeScreen) {
        composable<Destination.HomeScreen> {
            HomeScreen()
        }
    }
}


fun NavGraphBuilder.unauthorizedNavGraph() {
    navigation<Destination.UnauthorizedGroup>(startDestination = Destination.WelcomeScreen) {
        composable<Destination.WelcomeScreen> {
            val viewModel = koinViewModel<WelcomeViewModel>()
            WelcomeScreen(viewModel)
        }
        composable<Destination.LoginScreen> {
            val viewModel = koinViewModel<LoginViewModel>()
            LoginScreen(viewModel)
        }
        composable<Destination.RegisterScreen> {
            val viewModel = koinViewModel<RegisterViewModel>()
            RegisterScreen(viewModel)
        }
        composable<Destination.ActivateAccountScreen> {
            val viewModel = koinViewModel<ActivateAccountViewModel>()
            ActivateAccountScreen(viewModel)
        }
    }
}