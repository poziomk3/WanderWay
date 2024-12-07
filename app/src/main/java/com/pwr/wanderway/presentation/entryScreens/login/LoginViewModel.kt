package com.pwr.wanderway.presentation.entryScreens.login

import androidx.lifecycle.ViewModel
import com.pwr.wanderway.presentation.entryScreens.AuthViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val authViewModel: AuthViewModel
) : ViewModel() {

    val isLoading: Flow<Boolean> = authViewModel.authState.map { it.isLoading }
    val errorMessage: Flow<String?> = authViewModel.authState.map { it.errorMessage }
    val isLoginSuccessful: Flow<Boolean> = authViewModel.authState.map { it.isSuccess }


    fun onLoginClicked(username: String, password: String) {
        if (username.isBlank()) {
            authViewModel.setError(LoginError.UsernameBlank.message)
            return
        }

        if (password.isBlank()) {
            authViewModel.setError(LoginError.PasswordBlank.message)
            return
        }

        authViewModel.loginUser(username, password)
    }

    fun resetLoginState() {
        authViewModel.resetState() // Resets unified AuthState
    }
}

sealed class LoginError(val message: String) {
    object UsernameBlank : LoginError("Username cannot be blank.")
    object PasswordBlank : LoginError("Password cannot be blank.")
}