package com.pwr.wanderway.coreViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    // State for login status
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    // States for UI-specific feedback
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun checkLoginStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            val hasToken = authRepository.hasToken()
            withContext(Dispatchers.Main) {
                _isLoggedIn.value = hasToken
            }
        }
    }

    fun loginUser(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            _errorMessage.value = "Username and password cannot be empty."
            return
        }

        _isLoading.value = true
        _errorMessage.value = null
        viewModelScope.launch(Dispatchers.IO) {
            val result = authRepository.loginUser(username, password)
            withContext(Dispatchers.Main) {
                _isLoading.value = false
                if (result.isSuccess) {
                    _isLoggedIn.value = true
                } else {
                    _errorMessage.value = "Login failed. Please try again."
                }
            }
        }
    }

    fun registerUser(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ) {
        if (email.isBlank() || username.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            _errorMessage.value = "All fields must be filled."
            return
        }

        if (password != confirmPassword) {
            _errorMessage.value = "Passwords do not match."
            return
        }

        _isLoading.value = true
        _errorMessage.value = null
        viewModelScope.launch(Dispatchers.IO) {
            val result = authRepository.registerUser(email, username, password, confirmPassword)
            withContext(Dispatchers.Main) {
                _isLoading.value = false
                if (result.isSuccess) {
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "Registration failed. Please try again."
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.logout()
            withContext(Dispatchers.Main) {
                _isLoggedIn.value = false
            }
        }
    }
}
