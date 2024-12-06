package com.pwr.wanderway.coreViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.data.model.LoginRequest
import com.pwr.wanderway.data.model.RegisterRequest
import com.pwr.wanderway.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _isCheckingLogin = MutableStateFlow(true)
    val isCheckingLogin: StateFlow<Boolean> get() = _isCheckingLogin

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private val _isRegistrationSuccessful = MutableStateFlow(false)
    val isRegistrationSuccessful: StateFlow<Boolean> get() = _isRegistrationSuccessful

    fun checkLoginStatus() {
        viewModelScope.launch {
            runCatching {
                _isCheckingLogin.emit(true)
                authRepository.hasToken()
            }.onSuccess { hasToken ->
                _isLoggedIn.emit(hasToken)
            }.onFailure {
                _isLoggedIn.emit(false)
                _errorMessage.emit("Error checking login status.")
            }.also {
                _isCheckingLogin.emit(false)
            }
        }
    }

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            runCatching {
                _isLoading.emit(true)
                _errorMessage.emit(null)
                authRepository.login(LoginRequest(username, password))
            }.onSuccess { result ->
                if (result.isSuccess) {
                    _isLoggedIn.emit(true)
                    _errorMessage.emit(null)
                } else {
                    _isLoggedIn.emit(false)
                    _errorMessage.emit(result.exceptionOrNull()?.message ?: "Login failed.")
                }
            }.onFailure {
                _isLoggedIn.emit(false)
                _errorMessage.emit(it.message ?: "Unknown login error.")
            }.also {
                _isLoading.emit(false)
            }
        }
    }

    fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            runCatching {
                _isLoading.emit(true)
                _errorMessage.emit(null)
                authRepository.register(registerRequest)
            }.onSuccess { result ->
                if (result.isSuccess) {
                    _isRegistrationSuccessful.emit(true)
                    _errorMessage.emit(null)
                } else {
                    _isRegistrationSuccessful.emit(false)
                    _errorMessage.emit(result.exceptionOrNull()?.message ?: "Registration failed.")
                }
            }.onFailure {
                _isRegistrationSuccessful.emit(false)
                _errorMessage.emit(it.message ?: "Unknown registration error.")
            }.also {
                _isLoading.emit(false)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            runCatching {
                authRepository.logout()
            }.onSuccess {
                _isLoggedIn.emit(false)
            }.onFailure {
                _errorMessage.emit("Failed to logout.")
            }
        }
    }

    fun resetRegistrationState() {
        _isRegistrationSuccessful.tryEmit(false)
        _errorMessage.tryEmit(null)
    }

    fun resetLoginState() {
        _isLoggedIn.tryEmit(false)
        _errorMessage.tryEmit(null)
    }

    fun setErrorMessage(message: String) {
        _errorMessage.tryEmit(message)
    }
}


