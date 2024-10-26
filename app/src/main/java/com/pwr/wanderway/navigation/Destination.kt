package com.pwr.wanderway.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object WelcomeScreen : Destination

    @Serializable
    data object RegisterScreen : Destination

    @Serializable
    data object LoginScreen : Destination

    @Serializable
    data object ActivateAccountScreen : Destination
}