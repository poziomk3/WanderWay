package com.pwr.wanderway.presentation.entryScreens.login

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.coreViewModels.AuthViewModel
import com.pwr.wanderway.presentation.commons.Loader
import com.pwr.wanderway.presentation.commons.OnPrimaryTextField
import com.pwr.wanderway.presentation.entryScreens.commons.EntryScreenLayout

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = LoginViewModel(authViewModel), // Hilt-injected LoginViewModel
    onLoginSuccess: () -> Unit,
    onBackClick: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isLoading by loginViewModel.isLoading.collectAsState(initial = false)
    val errorMessage by loginViewModel.errorMessage.collectAsState(initial = null)
    val isLoggedIn by loginViewModel.isLoggedIn.collectAsState(initial = false)

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            loginViewModel.resetLoginState()
            onLoginSuccess()
        }
    }

    EntryScreenLayout(
        title = stringResource(id = R.string.entry_screen_label_login),
        content = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(modifier = Modifier.align(Alignment.Center)) {
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
                    if (errorMessage != null)
                        Text(
                            text = errorMessage ?: "",
                            color = MaterialTheme.colorScheme.error
                        )
                    else
                        Text("")
                }
                if (isLoading) {
                    Loader()
                }
            }
        },
        leftButton = stringResource(id = R.string.entry_screen_go_back),
        leftButtonOnClick = {
            loginViewModel.resetLoginState()
            onBackClick()
        },
        rightButton = stringResource(id = R.string.entry_screen_login),
        rightButtonOnClick = {
            if (!isLoading) {
                loginViewModel.onLoginClicked(username, password)
            }
        },
    )
}
