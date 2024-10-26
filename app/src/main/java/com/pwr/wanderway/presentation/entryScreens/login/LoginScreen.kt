package com.pwr.wanderway.presentation.entryScreens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.OnPrimaryTextField
import com.pwr.wanderway.presentation.entryScreens.commons.WelcomeBackgroundWrapper
import com.pwr.wanderway.presentation.entryScreens.commons.WelcomeDialog

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    WelcomeBackgroundWrapper {
        WelcomeDialog(
            title = stringResource(id = R.string.login_label),
            content = {
                Column {
                    OnPrimaryTextField(
                        value = "",
                        onValueChange = {},
                        label = stringResource(id = R.string.login)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    OnPrimaryTextField(
                        value = "",
                        onValueChange = {},
                        label = stringResource(id = R.string.password)
                    )


                }
            },
            confirmButtonText = stringResource(id = R.string.login),
            onConfirm = { viewModel.onLoginClicked() },
            dismissButtonText = stringResource(id = R.string.go_back),
            onDismiss = { viewModel.onGoBackClicked() })

    }
}

