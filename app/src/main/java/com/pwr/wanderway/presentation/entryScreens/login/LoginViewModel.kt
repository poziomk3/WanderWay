package com.pwr.wanderway.presentation.entryScreens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var isLoading = mutableStateOf(false)
    var loginError = mutableStateOf<String?>(null)
    var loginSuccess = mutableStateOf(false)

    fun onLoginClicked(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            loginError.value = "Username and password cannot be empty."
            return
        }

        isLoading.value = true
        viewModelScope.launch {
            val result = authRepository.loginUser(username, password)
            isLoading.value = false
            when {
                result.isSuccess -> {
                    loginError.value = null
                    loginSuccess.value = true
                }

                result.isFailure -> {
                    loginError.value = "Login failed. Please try again."
                    loginSuccess.value = false
                }
            }
        }
    }
}
