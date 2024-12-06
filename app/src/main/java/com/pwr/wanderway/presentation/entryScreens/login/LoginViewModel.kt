package com.pwr.wanderway.presentation.entryScreens.login

import androidx.lifecycle.ViewModel
import com.pwr.wanderway.coreViewModels.AuthViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val authViewModel: AuthViewModel
) : ViewModel() {

    val isLoading: StateFlow<Boolean> = authViewModel.isLoading
    val errorMessage: StateFlow<String?> = authViewModel.errorMessage
    val isLoggedIn: StateFlow<Boolean> = authViewModel.isLoggedIn

    fun onLoginClicked(username: String, password: String) {
        if (username.isBlank()) {
            authViewModel.setErrorMessage(LoginError.UsernameBlank.message)
            return
        }

        if (password.isBlank()) {
            authViewModel.setErrorMessage(LoginError.PasswordBlank.message)
            return
        }

        authViewModel.loginUser(username, password)
    }

    fun resetLoginState() {
        authViewModel.resetLoginState()
    }
}

sealed class LoginError(val message: String) {
    object UsernameBlank : LoginError("Username cannot be blank.")
    object PasswordBlank : LoginError("Password cannot be blank.")
}