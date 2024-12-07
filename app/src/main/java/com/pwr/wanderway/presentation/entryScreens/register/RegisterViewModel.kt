package com.pwr.wanderway.presentation.entryScreens.register

import androidx.lifecycle.ViewModel
import com.pwr.wanderway.presentation.entryScreens.AuthViewModel
import com.pwr.wanderway.data.model.RegisterRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RegisterViewModel(
    private val authViewModel: AuthViewModel
) : ViewModel() {

    // Expose relevant parts of AuthState
    val isLoading: Flow<Boolean> = authViewModel.authState.map { it.isLoading }
    val errorMessage: Flow<String?> = authViewModel.authState.map { it.errorMessage }
    val isRegistrationSuccessful: Flow<Boolean> = authViewModel.authState.map { it.isSuccess }

    fun onRegisterClicked(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ) {
        when {
            email.isBlank() -> {
                authViewModel.setError(RegistrationError.EmailBlank.message) // Use setError
                return
            }

            username.isBlank() -> {
                authViewModel.setError(RegistrationError.UsernameBlank.message) // Use setError
                return
            }

            password.isBlank() -> {
                authViewModel.setError(RegistrationError.PasswordBlank.message) // Use setError
                return
            }

            confirmPassword.isBlank() -> {
                authViewModel.setError(RegistrationError.ConfirmPasswordBlank.message) // Use setError
                return
            }

            password != confirmPassword -> {
                authViewModel.setError(RegistrationError.PasswordMismatch.message) // Use setError
                return
            }
        }

        authViewModel.registerUser(RegisterRequest(email, username, password, confirmPassword))
    }

    fun resetRegistrationState() {
        authViewModel.resetState() // Resets unified AuthState
    }
}


sealed class RegistrationError(val message: String) {
    object EmailBlank : RegistrationError("Email cannot be blank.")
    object UsernameBlank : RegistrationError("Username cannot be blank.")
    object PasswordBlank : RegistrationError("Password cannot be blank.")
    object ConfirmPasswordBlank : RegistrationError("Confirm password cannot be blank.")
    object PasswordMismatch : RegistrationError("Passwords do not match.")
}