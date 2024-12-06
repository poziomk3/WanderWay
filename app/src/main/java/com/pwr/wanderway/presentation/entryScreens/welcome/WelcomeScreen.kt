package com.pwr.wanderway.presentation.entryScreens.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.coreViewModels.AuthViewModel
import com.pwr.wanderway.presentation.entryScreens.commons.EntryScreenLayout
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun WelcomeScreen(
    authViewModel: AuthViewModel = hiltViewModel(), // Inject AuthViewModel
    welcomeViewModel: WelcomeViewModel = viewModel { WelcomeViewModel(authViewModel) }, // Create WelcomeViewModel
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onAlreadyLoggedIn: () -> Unit
) {
    // Observe isLoggedIn state using collectAsState
    val isLoggedIn = welcomeViewModel.isLoggedIn.collectAsState(initial = false)

    if (isLoggedIn.value) {
        // Navigate to the main screen or execute the callback
        onAlreadyLoggedIn()
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
