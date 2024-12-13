package com.pwr.wanderway.presentation.entryScreens.welcome

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.Loader
import com.pwr.wanderway.presentation.entryScreens.AuthViewModel
import com.pwr.wanderway.presentation.entryScreens.commons.EntryScreenLayout
import kotlinx.coroutines.flow.map

@Composable
fun WelcomeScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    val isCheckingLoginFlow = remember(authViewModel) {
        authViewModel.authState.map { it.isLoading }
    }
    val isCheckingLogin by isCheckingLoginFlow.collectAsState(initial = true)

    LaunchedEffect(Unit) {
        authViewModel.isLoggedIn()
    }




    if (isCheckingLogin) {
        Loader(color = MaterialTheme.colorScheme.onPrimary)
    } else {
        EntryScreenLayout(
            title = stringResource(id = R.string.entry_screen_title),
            content = {
                Text(
                    stringResource(id = R.string.entry_screen_subtitle),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            rightButton = stringResource(id = R.string.entry_screen_login),
            leftButton = stringResource(id = R.string.entry_screen_register),
            rightButtonOnClick = { onLoginClick() },
            leftButtonOnClick = { onRegisterClick() },
        )
    }
}

