package com.pwr.wanderway.presentation.entryScreens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class LoginViewModel() : ViewModel() {

    val isDialogVisible = mutableStateOf(true)
    fun onGoBackClicked() {

        isDialogVisible.value = false
    }

    fun onLoginClicked(username: String, password: String) {

        isDialogVisible.value = false
    }
}
