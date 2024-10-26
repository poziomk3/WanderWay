package com.pwr.wanderway.presentation.entryScreens.welcome

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.Navigator
import kotlinx.coroutines.launch

class WelcomeViewModel(private val navigator: Navigator) : ViewModel() {

    val isDialogVisible: MutableState<Boolean> = mutableStateOf(true)
    fun onLoginClicked() {
        viewModelScope.launch {
            navigator.navigate(Destination.LoginScreen)
            isDialogVisible.value = false
        }
    }

    fun onRegisterClicked() {
        viewModelScope.launch {
            navigator.navigate(Destination.RegisterScreen)
            isDialogVisible.value = false
        }
    }
}