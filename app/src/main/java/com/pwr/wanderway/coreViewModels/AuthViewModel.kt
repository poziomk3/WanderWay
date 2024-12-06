package com.pwr.wanderway.coreViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.data.model.LoginRequest
import com.pwr.wanderway.data.model.RegisterRequest
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

    private val _isRegistrationSuccessful = MutableStateFlow(false)
    val isRegistrationSuccessful: StateFlow<Boolean> get() = _isRegistrationSuccessful

    fun checkLoginStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            val hasToken = authRepository.hasToken()
            withContext(Dispatchers.Main) {
                _isLoggedIn.value = hasToken
            }
        }
    }

    fun loginUser(username: String, password: String) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch(Dispatchers.IO) {
            val result = authRepository.login(LoginRequest(username, password))

            withContext(Dispatchers.Main) {
                _isLoading.value = false
                if (result.isSuccess) {
                    _isLoggedIn.value = true
                    _errorMessage.value = null
                } else {
                    _isLoggedIn.value = false
                    _errorMessage.value = result.exceptionOrNull()?.message ?: "Unknown error occurred."
                }
            }
        }
    }


    fun registerUser(registerRequest: RegisterRequest) {
        _isLoading.value = true
        _errorMessage.value = null
        _isRegistrationSuccessful.value = false

        viewModelScope.launch(Dispatchers.IO) {
            val result = authRepository.register(registerRequest)
            withContext(Dispatchers.Main) {
                _isLoading.value = false
                if (result.isSuccess) {
                    _isRegistrationSuccessful.value = true
                    _errorMessage.value = null
                } else {
                    _isRegistrationSuccessful.value = false
                    _errorMessage.value = result.exceptionOrNull()?.message ?: "Unknown error occurred."
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

    fun resetRegistrationState() {
        _isRegistrationSuccessful.value = false
        _errorMessage.value = null
    }

    fun resetLoginState() {
        _isLoggedIn.value = false
        _errorMessage.value = null
    }

    fun setErrorMessage(message: String) {
        _errorMessage.value = message
    }
}

