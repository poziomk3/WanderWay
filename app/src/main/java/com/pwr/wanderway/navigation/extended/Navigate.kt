package com.pwr.wanderway.navigation.extended

import androidx.navigation.NavHostController
import com.pwr.wanderway.navigation.Destination

fun NavHostController.navigateTo(destination: Destination) {
    this.navigate(destination.route)
}


