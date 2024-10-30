package com.pwr.wanderway.presentation.navbar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthenticatedWrapperViewModel(private val navigator: Navigator) : ViewModel() {
    private val _currentDestination = MutableStateFlow<Destination>(navigator.startDestination)
    val currentDestination: StateFlow<Destination> = _currentDestination

    init {
        viewModelScope.launch {
            navigator.currentDestinationFlow.collect { newDestination ->
                _currentDestination.value = newDestination
            }
        }
    }

    fun onHomeClicked() {
        viewModelScope.launch { navigator.navigate(Destination.HomeScreen) }
    }

    fun onAccountSettingsClicked() {
        viewModelScope.launch { navigator.navigate(Destination.AccountSettingsHomeScreen) }
    }

    fun onForumClicked() {
        viewModelScope.launch { navigator.navigate(Destination.ForumHomeScreen) }
    }

}