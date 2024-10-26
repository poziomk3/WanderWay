package com.pwr.wanderway.presentation.entryScreens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.Navigator
import kotlinx.coroutines.launch

class LoginViewModel(private val navigator: Navigator) : ViewModel() {

    fun onLoginClicked() {
        viewModelScope.launch {
            navigator.navigate(Destination.WelcomeScreen)
        }
    }

    fun onGoBackClicked() {
        viewModelScope.launch {
            navigator.navigate(Destination.WelcomeScreen)
        }
    }
}
