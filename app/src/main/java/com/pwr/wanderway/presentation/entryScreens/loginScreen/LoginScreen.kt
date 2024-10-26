package com.pwr.wanderway.presentation.entryScreens.loginScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.OnPrimaryTextField
import com.pwr.wanderway.presentation.entryScreens.commons.WelcomeDialog
import com.pwr.wanderway.presentation.entryScreens.commons.WelcomeBackgroundWrapper
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun LoginScreen() {
    WelcomeBackgroundWrapper {
        WelcomeDialog(title = stringResource(id = R.string.login_label), content = {
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
        }, confirmButtonText = stringResource(id = R.string.login))

    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    AppTheme {
        LoginScreen()
    }
}