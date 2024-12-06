package com.pwr.wanderway.presentation.entryScreens.register

import androidx.lifecycle.ViewModel
import com.pwr.wanderway.coreViewModels.AuthViewModel

class RegisterViewModel(
    private val authViewModel: AuthViewModel
) : ViewModel() {

    val isLoading = authViewModel.isLoading
    val errorMessage = authViewModel.errorMessage

    fun onRegisterClicked(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ) {
        authViewModel.registerUser(email, username, password, confirmPassword)
    }
}
