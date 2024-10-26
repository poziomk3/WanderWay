package com.pwr.wanderway.presentation.entryScreens.register

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
fun RegisterScreen(viewModel: RegisterViewModel) {
    WelcomeBackgroundWrapper {
        WelcomeDialog(
            title = stringResource(id = R.string.register),
            content = {
                Column {
                    OnPrimaryTextField(
                        value = "",
                        onValueChange = {},
                        label = stringResource(id = R.string.email)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
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
                    Spacer(modifier = Modifier.size(16.dp))
                    OnPrimaryTextField(
                        value = "",
                        onValueChange = {},
                        label = stringResource(id = R.string.password)
                    )


                }
            },
            onDismiss = { viewModel.onGoBackClicked() },
            onConfirm = { viewModel.onRegisterClicked() },
            confirmButtonText = stringResource(id = R.string.register),
            dismissButtonText = stringResource(id = R.string.go_back)
        )

    }
}

