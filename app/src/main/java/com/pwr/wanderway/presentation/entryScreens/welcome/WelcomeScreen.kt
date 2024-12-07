package com.pwr.wanderway.presentation.entryScreens.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.entryScreens.AuthViewModel
import com.pwr.wanderway.presentation.commons.Loader
import com.pwr.wanderway.presentation.entryScreens.commons.EntryScreenLayout
import com.pwr.wanderway.ui.theme.AppTheme
import kotlinx.coroutines.flow.map

@Composable
fun WelcomeScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onAlreadyLoggedIn: () -> Unit
) {
    val isCheckingLoginFlow = remember(authViewModel) {
        authViewModel.authState.map { it.isLoading }
    }
    val isCheckingLogin by isCheckingLoginFlow.collectAsState(initial = true)
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState(initial = false)

    LaunchedEffect(Unit) {
        authViewModel.isLoggedIn()
    }

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            onAlreadyLoggedIn()
        }
    }


    if (isCheckingLogin) {
        Loader()
    } else {
        EntryScreenLayout(
            title = stringResource(id = R.string.entry_screen_title),
            content = {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(stringResource(id = R.string.entry_screen_subtitle))
                }
            },
            rightButton = stringResource(id = R.string.entry_screen_login),
            leftButton = stringResource(id = R.string.entry_screen_register),
            rightButtonOnClick = { onLoginClick() },
            leftButtonOnClick = { onRegisterClick() },
        )
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {

    AppTheme {
        WelcomeScreen(
            onLoginClick = {},
            onRegisterClick = {},
            onAlreadyLoggedIn = {}
        )
    }
}
