package com.pwr.wanderway.presentation.entryScreens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoginViewModel() : ViewModel() {

    var isLoading = mutableStateOf(false)
    var loginError = mutableStateOf<String?>(null)

    // Simulated login function
    fun onLoginClicked(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            loginError.value = "Username and password cannot be empty."
            return
        }

        // Simulate a network call or repository interaction
        isLoading.value = true
        viewModelScope.launch {
            delay(2000) // Simulate network delay
            if (username == "test" && password == "password") {
                // Simulate successful login
                loginError.value = null
            } else {
                // Simulate failed login
                loginError.value = "Invalid username or password."
            }
            isLoading.value = false
        }
    }
}
