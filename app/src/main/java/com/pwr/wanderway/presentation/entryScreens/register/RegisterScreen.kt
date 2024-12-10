package com.pwr.wanderway.presentation.entryScreens.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.Loader
import com.pwr.wanderway.presentation.commons.OnPrimaryTextField
import com.pwr.wanderway.presentation.entryScreens.AuthViewModel
import com.pwr.wanderway.presentation.entryScreens.commons.EntryScreenLayout
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    registerViewModel: RegisterViewModel = RegisterViewModel(authViewModel),
    onRegisterSuccess: () -> Unit,
    onGoBackClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val isLoading by registerViewModel.isLoading.collectAsState(initial = false)
    val errorMessage by registerViewModel.errorMessage.collectAsState(initial = null)

    val isRegistrationSuccessful =
        registerViewModel.isRegistrationSuccessful.collectAsState(initial = false)


    LaunchedEffect(Unit) { authViewModel.resetState() }

    LaunchedEffect(isRegistrationSuccessful.value) {
        if (isRegistrationSuccessful.value) {
            onRegisterSuccess()
        }
    }

    EntryScreenLayout(
        title = stringResource(id = R.string.entry_screen_register),
        content = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column {
                    OnPrimaryTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = stringResource(id = R.string.entry_screen_email)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
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
                    OnPrimaryTextField(
                        visualTransformation = PasswordVisualTransformation(),
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = stringResource(id = R.string.entry_screen_repeat_password)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    if (errorMessage != null)
                        Text(
                            text = errorMessage ?: "",
                            color = MaterialTheme.colorScheme.error
                        )
                    else
                        Text("")
                }
                if (isLoading) {
                    Loader(color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        },
        leftButton = stringResource(id = R.string.entry_screen_go_back),
        leftButtonOnClick = {
            onGoBackClick()
        },
        rightButton = stringResource(id = R.string.entry_screen_register),
        rightButtonOnClick = {
            registerViewModel.onRegisterClicked(email, username, password, confirmPassword)
        },
    )
}

@Preview
@Composable
fun RegisterScreenPreview() {

    AppTheme {
        RegisterScreen(
            onRegisterSuccess = {},
            onGoBackClick = {},
            authViewModel = hiltViewModel()
        )
    }
}
