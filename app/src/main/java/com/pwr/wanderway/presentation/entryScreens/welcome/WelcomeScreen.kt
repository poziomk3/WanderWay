package com.pwr.wanderway.presentation.entryScreens.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.entryScreens.commons.WelcomeBackgroundWrapper
import com.pwr.wanderway.presentation.entryScreens.commons.WelcomeDialog
import com.pwr.wanderway.ui.theme.AppTheme


@Composable
fun WelcomeScreen(
    navController: NavController,
    viewModel: WelcomeViewModel = WelcomeViewModel(ComposableNavigator(navController))
) {
    WelcomeBackgroundWrapper {
        WelcomeDialog(
            title = stringResource(id = R.string.welcome_label),
            content = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
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

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    AppTheme {
        WelcomeScreen(navController = rememberNavController())
    }
}
