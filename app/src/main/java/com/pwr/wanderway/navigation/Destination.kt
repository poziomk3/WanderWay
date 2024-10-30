package com.pwr.wanderway.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object UnauthorizedGroup : Destination
    @Serializable
    data object WelcomeScreen : Destination

    @Serializable
    data object RegisterScreen : Destination

    @Serializable
    data object LoginScreen : Destination

    @Serializable
    data object ActivateAccountScreen : Destination


//    authorized

    @Serializable
    data object AuthorizedGroup : Destination

    @Serializable
    data object HomeScreen : Destination
}