package com.pwr.wanderway.presentation.entryScreens.welcome

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.navigation.Destination
import kotlinx.coroutines.launch

class WelcomeViewModel() : ViewModel() {

    val isDialogVisible: MutableState<Boolean> = mutableStateOf(true)
    fun onLoginClicked() {
            isDialogVisible.value = false
    }

    fun onRegisterClicked() {
            isDialogVisible.value = false
    }
}