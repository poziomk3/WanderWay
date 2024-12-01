package com.pwr.wanderway.presentation.entryScreens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.OnPrimaryTextField
import com.pwr.wanderway.presentation.entryScreens.commons.EntryScreenLayout
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
    onBackClick: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isLoading by viewModel.isLoading
    val loginError by viewModel.loginError
    val loginSuccess by viewModel.loginSuccess

    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
            onLoginSuccess()
        }
    }

    EntryScreenLayout(
        title = stringResource(id = R.string.entry_screen_label_login),
        content = {
            Column {
                OnPrimaryTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = stringResource(id = R.string.entry_screen_login)
                )
                Spacer(modifier = Modifier.size(16.dp))
                OnPrimaryTextField(
                    visualTransformation = PasswordVisualTransformation(),
                    value = password,
                    onValueChange = { password = it },
                    label = stringResource(id = R.string.entry_screen_password)
                )
                Spacer(modifier = Modifier.size(16.dp))
                if (loginError != null)
                    Text(
                        text = loginError!!,
                        color = MaterialTheme.colorScheme.errorContainer
                    )
                if (isLoading)
                    CircularProgressIndicator()
            }
        },
        leftButton = stringResource(id = R.string.entry_screen_go_back),
        leftButtonOnClick = {
            onBackClick()
        },
        rightButton = stringResource(id = R.string.entry_screen_login),
        rightButtonOnClick = {
            viewModel.onLoginClicked(username, password)
        },
    )
}


@Preview
@Composable
fun LoginScreenPreview() {
    AppTheme {
        LoginScreen(hiltViewModel(), {}, {})
    }
}
