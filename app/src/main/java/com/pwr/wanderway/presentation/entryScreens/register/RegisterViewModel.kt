package com.pwr.wanderway.presentation.entryScreens.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.Navigator
import kotlinx.coroutines.launch

class RegisterViewModel(private val navigator: Navigator) : ViewModel() {
    var isDialogVisible = mutableStateOf(true)
    fun onRegisterClicked() {
        viewModelScope.launch {
            navigator.navigate(Destination.ActivateAccountScreen)
            isDialogVisible.value = false
        }
    }

    fun onGoBackClicked() {
        viewModelScope.launch {
            navigator.navigate(Destination.WelcomeScreen)
            isDialogVisible.value = false
        }
    }

}
