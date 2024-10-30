package com.pwr.wanderway.navigation

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

interface Navigator {
    val startDestination: Destination
    val navigationActions: Flow<NavigationAction>
    val currentDestinationFlow: Flow<Destination>
    suspend fun navigate(
        destination: Destination,
        navOptions: NavOptionsBuilder.() -> Unit = {}
    )

    suspend fun navigateUp()
}

class DefaultNavigator(
    override val startDestination: Destination
): Navigator {
    private val _navigationActions = Channel<NavigationAction>()
    override val navigationActions = _navigationActions.receiveAsFlow()
    private val _currentDestination = MutableStateFlow(startDestination)
    override val currentDestinationFlow = _currentDestination.asStateFlow()
    override suspend fun navigate(
        destination: Destination,
        navOptions: NavOptionsBuilder.() -> Unit
    ) {
        _currentDestination.value = destination
        _navigationActions.send(
            NavigationAction.Navigate(
                destination = destination,
                navOptions = navOptions
            )
        )
    }

    override suspend fun navigateUp() {
        _navigationActions.send(NavigationAction.NavigateUp)
    }
}