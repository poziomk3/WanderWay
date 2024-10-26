package com.pwr.wanderway.presentation.entryScreens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WelcomeViewModel : ViewModel() {
    fun onLoginClicked() {
        viewModelScope.launch {
            // Handle login logic
        }
    }

    fun onRegisterClicked() {
        viewModelScope.launch {
            // Handle registration logic
        }
    }
}