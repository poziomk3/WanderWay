package com.pwr.wanderway.presentation.entryScreens.welcome

import androidx.lifecycle.ViewModel
import com.pwr.wanderway.coreViewModels.AuthViewModel

class WelcomeViewModel(
    private val authViewModel: AuthViewModel
) : ViewModel() {

    val isLoggedIn = authViewModel.isLoggedIn

    init{
        checkLoginStatus()
    }
    fun checkLoginStatus() {
        authViewModel.checkLoginStatus()
    }

    fun logout() {
        authViewModel.logout()
    }
}
