package com.pwr.wanderway.presentation.entryScreens.register

import androidx.lifecycle.ViewModel
import com.pwr.wanderway.coreViewModels.AuthViewModel
import com.pwr.wanderway.data.model.RegisterRequest
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel(
    private val authViewModel: AuthViewModel
) : ViewModel() {

    val isLoading: StateFlow<Boolean> = authViewModel.isLoading
    val errorMessage: StateFlow<String?> = authViewModel.errorMessage
    val isRegistrationSuccessful: StateFlow<Boolean> = authViewModel.isRegistrationSuccessful

    fun onRegisterClicked(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ) {
        when {
            email.isBlank() -> {
                authViewModel.setErrorMessage(RegistrationError.EmailBlank.message)
                return
            }

            username.isBlank() -> {
                authViewModel.setErrorMessage(RegistrationError.UsernameBlank.message)
                return
            }

            password.isBlank() -> {
                authViewModel.setErrorMessage(RegistrationError.PasswordBlank.message)
                return
            }

            confirmPassword.isBlank() -> {
                authViewModel.setErrorMessage(RegistrationError.ConfirmPasswordBlank.message)
                return
            }

            password != confirmPassword -> {
                authViewModel.setErrorMessage(RegistrationError.PasswordMismatch.message)
                return
            }
        }
        authViewModel.registerUser(RegisterRequest(email, username, password, confirmPassword))
    }

    fun resetRegistrationState() {
        authViewModel.resetRegistrationState()
    }

}


sealed class RegistrationError(val message: String) {
    object EmailBlank : RegistrationError("Email cannot be blank.")
    object UsernameBlank : RegistrationError("Username cannot be blank.")
    object PasswordBlank : RegistrationError("Password cannot be blank.")
    object ConfirmPasswordBlank : RegistrationError("Confirm password cannot be blank.")
    object PasswordMismatch : RegistrationError("Passwords do not match.")
}