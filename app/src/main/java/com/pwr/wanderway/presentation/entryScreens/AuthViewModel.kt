package com.pwr.wanderway.presentation.entryScreens

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


data class AuthState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> get() = _authState


    suspend fun isLoggedIn(): Boolean {
        return runCatching {
            authRepository.hasToken()
        }.onSuccess { hasToken ->
            updateState(
                isLoading = false,
                isSuccess = hasToken,
                errorMessage = if (hasToken) null else "User not logged in."
            )
        }.onFailure { exception ->
            updateState(
                isLoading = false,
                isSuccess = false,
                errorMessage = exception.message ?: "Error checking login status."
            )
        }.getOrDefault(false)
    }


    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            updateState(isLoading = true)
            runCatching {
                authRepository.login(LoginRequest(username, password))
            }.onSuccess { result ->
                if (result.isSuccess) {
                    updateState(isSuccess = true, errorMessage = null)
                } else {
                    updateState(
                        isSuccess = false,
                        errorMessage = result.exceptionOrNull()?.message ?: "Login failed."
                    )
                }
            }.onFailure {
                updateState(isSuccess = false, errorMessage = it.message ?: "Unknown login error.")
            }
        }
    }

    fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            updateState(isLoading = true)
            runCatching {
                authRepository.register(registerRequest)
            }.onSuccess { result ->
                if (result.isSuccess) {
                    updateState(isSuccess = true, errorMessage = null)
                } else {
                    updateState(
                        isSuccess = false,
                        errorMessage = result.exceptionOrNull()?.message ?: "Registration failed."
                    )
                }
            }.onFailure {
                updateState(
                    isSuccess = false,
                    errorMessage = it.message ?: "Unknown registration error."
                )
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            runCatching {
                authRepository.logout()
            }.onSuccess {
                resetState()
            }.onFailure {
                setError("Failed to logout.")
            }
        }
    }

    private suspend fun updateState(
        isLoading: Boolean = false,
        isSuccess: Boolean = false,
        errorMessage: String? = null
    ) {
        _authState.emit(AuthState(isLoading, isSuccess, errorMessage))
    }

    fun resetState() {
        _authState.tryEmit(AuthState())
    }

    fun setError(message: String) {
        _authState.tryEmit(_authState.value.copy(errorMessage = message))
    }
}


