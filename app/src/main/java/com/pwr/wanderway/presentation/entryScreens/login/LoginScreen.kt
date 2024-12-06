package com.pwr.wanderway.presentation.entryScreens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.pwr.wanderway.coreViewModels.AuthViewModel
import com.pwr.wanderway.presentation.commons.OnPrimaryTextField
import com.pwr.wanderway.presentation.entryScreens.commons.EntryScreenLayout
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = hiltViewModel(), // Hilt-injected AuthViewModel
    loginViewModel: LoginViewModel = LoginViewModel(authViewModel), // Create LoginViewModel in params
    onLoginSuccess: () -> Unit,
    onBackClick: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Use collectAsState to observe StateFlow values
    val isLoading = loginViewModel.isLoading.collectAsState(initial = false)
    val errorMessage = loginViewModel.errorMessage.collectAsState(initial = null)
    val isLoggedIn = loginViewModel.isLoggedIn.collectAsState(initial = false)

    LaunchedEffect(isLoggedIn.value) {
        if (isLoggedIn.value) {
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
                if (!errorMessage.value.isNullOrEmpty())
                    Text(
                        text = errorMessage.value!!,
                        color = MaterialTheme.colorScheme.error
                    )
                if (isLoading.value)
                    CircularProgressIndicator()
            }
        },
        leftButton = stringResource(id = R.string.entry_screen_go_back),
        leftButtonOnClick = {
            onBackClick()
        },
        rightButton = stringResource(id = R.string.entry_screen_login),
        rightButtonOnClick = {
            loginViewModel.onLoginClicked(username, password)
        },
    )
}

@Preview
@Composable
fun LoginScreenPreview() {
    AppTheme {
        LoginScreen(
            onLoginSuccess = {},
            onBackClick = {}
        )
    }
}
