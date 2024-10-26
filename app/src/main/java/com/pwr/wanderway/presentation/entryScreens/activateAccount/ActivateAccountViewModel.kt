package com.pwr.wanderway.presentation.entryScreens.activateAccount

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.Navigator
import kotlinx.coroutines.launch

class ActivateAccountViewModel(private val navigator: Navigator) : ViewModel() {

    val isDialogVisible= mutableStateOf(true)

    fun onGotItClicked() {
        viewModelScope.launch {
            navigator.navigate(Destination.LoginScreen)
            isDialogVisible.value = false
        }
    }
}
