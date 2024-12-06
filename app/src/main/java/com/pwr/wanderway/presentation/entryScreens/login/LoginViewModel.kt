package com.pwr.wanderway.presentation.entryScreens.login

import androidx.lifecycle.ViewModel
import com.pwr.wanderway.coreViewModels.AuthViewModel

class LoginViewModel(
    private val authViewModel: AuthViewModel
) : ViewModel() {

    val isLoading = authViewModel.isLoading
    val errorMessage = authViewModel.errorMessage
    val isLoggedIn = authViewModel.isLoggedIn

    fun onLoginClicked(username: String, password: String) {
        authViewModel.loginUser(username, password)
    }
}
