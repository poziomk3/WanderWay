package com.pwr.wanderway.presentation.entryScreens.activateAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.Navigator
import kotlinx.coroutines.launch

class ActivateAccountViewModel(private val navigator: Navigator) : ViewModel() {

    fun onGotItClicked() {
        viewModelScope.launch {
            navigator.navigate(Destination.LoginScreen)
        }
    }
}
