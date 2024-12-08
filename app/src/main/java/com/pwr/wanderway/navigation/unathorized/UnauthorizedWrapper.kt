package com.pwr.wanderway.navigation.unathorized

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pwr.wanderway.presentation.entryScreens.AuthViewModel

@Composable
fun UnauthorizedWrapper(
    navController: NavHostController = rememberNavController(),
    moveToAuthorized: () -> Unit,
    authViewModel: AuthViewModel
) {
    UnauthorizedNavGraph(navController = navController, moveToAuthorized = moveToAuthorized, authViewModel = authViewModel)
}