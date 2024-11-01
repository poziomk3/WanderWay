package com.pwr.wanderway.presentation.entryScreens.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var isLoading = mutableStateOf(false)
    var registerError = mutableStateOf<String?>(null)
    var registerSuccess = mutableStateOf(false)

    fun onRegisterClicked(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ) {
        if (email.isBlank() || username.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            registerError.value = "All fields must be filled."
            return
        }

        if (!email.contains("@") || !email.contains(".")) {
            registerError.value = "Invalid email format."
            return
        }

        if (password != confirmPassword) {
            registerError.value = "Passwords do not match."
            return
        }

        if (password.length < 8) {
            registerError.value = "Password must be at least 8 characters long."
            return
        }

        isLoading.value = true
        viewModelScope.launch {
            val result = authRepository.registerUser(email, username, password, confirmPassword)
            isLoading.value = false
            when {
                result.isSuccess -> {
                    registerError.value = null
                    registerSuccess.value = true
                }

                result.isFailure -> {
                    registerError.value = "Registration failed. Please try again."
                    registerSuccess.value = false
                }
            }
        }
    }
}
