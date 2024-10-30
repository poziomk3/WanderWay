package com.pwr.wanderway.presentation.entryScreens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.Navigator
import kotlinx.coroutines.launch


class LoginViewModel(private val navigator: Navigator) : ViewModel() {

    val isDialogVisible = mutableStateOf(true)
    fun onGoBackClicked() {
        viewModelScope.launch {
            navigator.navigate(Destination.WelcomeScreen)
            isDialogVisible.value = false
        }
    }

    fun onLoginClicked(username: String, password: String) {
        viewModelScope.launch {
            navigator.navigate(Destination.AuthorizedGroup)
            isDialogVisible.value = false
        }
    }
}
