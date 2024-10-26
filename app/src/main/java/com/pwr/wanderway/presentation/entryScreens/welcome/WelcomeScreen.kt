package com.pwr.wanderway.presentation.entryScreens.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.entryScreens.commons.WelcomeBackgroundWrapper
import com.pwr.wanderway.presentation.entryScreens.commons.WelcomeDialog


@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel
) {
    WelcomeBackgroundWrapper {
        WelcomeDialog(
            title = stringResource(id = R.string.welcome_label),
            content = {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(stringResource(id = R.string.welcome_sublabel))
                }
            },
            confirmButtonText = stringResource(id = R.string.login),
            dismissButtonText = stringResource(id = R.string.register),
            onConfirm = { viewModel.onLoginClicked() },
            onDismiss = { viewModel.onRegisterClicked() }
        )
    }
}

