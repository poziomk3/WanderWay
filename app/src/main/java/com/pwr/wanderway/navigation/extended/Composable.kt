package com.pwr.wanderway.navigation.extended

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pwr.wanderway.navigation.Destination

fun NavGraphBuilder.composable(destination: Destination, content: @Composable () -> Unit) {
    this.composable(route = destination.route) {
        content()
    }
}