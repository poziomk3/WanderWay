package com.pwr.wanderway.presentation.entryScreens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.OnPrimaryTextField
import com.pwr.wanderway.presentation.entryScreens.commons.WelcomeBackgroundWrapper
import com.pwr.wanderway.presentation.entryScreens.commons.WelcomeDialog

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isErrorVisible by remember { mutableStateOf(false) }

    WelcomeBackgroundWrapper {
        WelcomeDialog(
            title = stringResource(id = R.string.login_label),
            content = {
                Column {
                    OnPrimaryTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = stringResource(id = R.string.login)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    OnPrimaryTextField(
                        visualTransformation = PasswordVisualTransformation(),
                        value = password,
                        onValueChange = { password = it },
                        label = stringResource(id = R.string.password)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    if (isErrorVisible)
                        Text(stringResource(id = R.string.error_empty_fields), color = MaterialTheme.colorScheme.errorContainer)


                }
            },
            rightButton = stringResource(id = R.string.login),
            rightButtonOnClick = {
                if (username.isEmpty() || password.isEmpty()) {
                    isErrorVisible = true
                }
                else viewModel.onLoginClicked(username, password)
            },
            leftButton = stringResource(id = R.string.go_back),
            leftButtonOnClick = { viewModel.onGoBackClicked() },
            isDialogVisible = viewModel.isDialogVisible,
        )
    }
}
