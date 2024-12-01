package com.pwr.wanderway.navigation.unathorized

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun UnauthorizedWrapper(
    navController: NavHostController = rememberNavController(),
    moveToAuthorized: () -> Unit
) {
    UnauthorizedNavGraph(navController = navController, moveToAuthorized = moveToAuthorized)
}