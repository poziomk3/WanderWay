package com.pwr.wanderway.navigation.overrides

import androidx.navigation.NavHostController
import com.pwr.wanderway.navigation.Destination

fun NavHostController.navigateTo(destination: Destination) {
    this.navigate(destination.route)
}


