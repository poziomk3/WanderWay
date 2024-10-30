package com.pwr.wanderway.presentation.navbar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.Navigator
import kotlinx.coroutines.launch

class AuthenticatedWrapperViewModel(private val navigator: Navigator) : ViewModel() {
    fun onSettingsClicked() {
        viewModelScope.launch {
            navigator.navigate(Destination.SettingsHomeScreen)
        }
    }
    fun onHomeClicked() {
        viewModelScope.launch {
            navigator.navigate(Destination.HomeScreen)
        }
    }
}