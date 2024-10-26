package com.pwr.wanderway

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pwr.wanderway.presentation.entryScreens.activateAccount.ActivateAccountScreen
import com.pwr.wanderway.presentation.entryScreens.login.LoginScreen
import com.pwr.wanderway.presentation.entryScreens.register.RegisterScreen
import com.pwr.wanderway.presentation.entryScreens.welcome.WelcomeScreen


enum class Screen(val route: String) {
    WELCOME("welcome"),
    REGISTER("register"),
    LOGIN("login"),
    ACTIVATE("activate")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.WELCOME.route) {
        composable(Screen.WELCOME.route) {
            WelcomeScreen(navController = navController)
        }
        composable(Screen.REGISTER.route) {
            RegisterScreen(navController = navController)
        }
        composable(Screen.LOGIN.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.ACTIVATE.route) {
            ActivateAccountScreen(navController = navController)
        }
    }
}