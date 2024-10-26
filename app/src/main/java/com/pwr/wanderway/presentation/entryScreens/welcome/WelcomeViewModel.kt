package com.pwr.wanderway.presentation.entryScreens.welcome

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.pwr.wanderway.Screen


interface Navigator {
    fun navigateToLogin()
    fun navigateToRegister()
}

class ComposableNavigator(private val navController: NavController) : Navigator {
    override fun navigateToLogin() {
        navController.navigate(Screen.LOGIN.route)
    }

    override fun navigateToRegister() {
        navController.navigate(Screen.REGISTER.route)
    }
}

class WelcomeViewModel(private val navigator: Navigator) : ViewModel() {
    fun onLoginClicked() {
        navigator.navigateToLogin()
    }

    fun onRegisterClicked() {
        navigator.navigateToRegister()
    }
}