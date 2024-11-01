package com.pwr.wanderway.presentation.entryScreens.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RegisterViewModel() : ViewModel() {
    var isDialogVisible = mutableStateOf(true)
    fun onRegisterClicked() {
            isDialogVisible.value = false
    }

    fun onGoBackClicked() {
            isDialogVisible.value = false
    }

}
