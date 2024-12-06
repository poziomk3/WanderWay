package com.pwr.wanderway.presentation.entryScreens.register

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.coreViewModels.AuthViewModel
import com.pwr.wanderway.presentation.commons.OnPrimaryTextField
import com.pwr.wanderway.presentation.entryScreens.commons.EntryScreenLayout
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel = hiltViewModel(), // Inject AuthViewModel
    registerViewModel: RegisterViewModel = viewModel { RegisterViewModel(authViewModel) }, // Pass to RegisterViewModel
    onRegisterSuccess: () -> Unit,
    onGoBackClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Use collectAsState to observe StateFlow values
    val isLoading = registerViewModel.isLoading.collectAsState(initial = false)
    val errorMessage = registerViewModel.errorMessage.collectAsState(initial = null)

    LaunchedEffect(isLoading.value) {
        if (isLoading.value) {
            onRegisterSuccess()
        }
    }

    EntryScreenLayout(
        title = stringResource(id = R.string.entry_screen_register),
        content = {
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
                if (!errorMessage.value.isNullOrEmpty()) {
                    Text(
                        text = errorMessage.value!!,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                if (isLoading.value) {
                    CircularProgressIndicator()
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
            onGoBackClick = {}
        )
    }
}
