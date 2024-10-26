package com.pwr.wanderway.presentation.entryScreens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.Navigator
import kotlinx.coroutines.launch

class WelcomeViewModel(private val navigator: Navigator) : ViewModel() {
    fun onLoginClicked() {
        viewModelScope.launch {
            navigator.navigate(Destination.LoginScreen)
        }
    }

    fun onRegisterClicked() {
        viewModelScope.launch {
            navigator.navigate(Destination.RegisterScreen)
        }
    }
}