package com.pwr.wanderway.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.data.local.TokenManager
import com.pwr.wanderway.data.repository.AuthRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val tokenManager = TokenManager(application)
    private val authRepository = AuthRepository(tokenManager)

    // Register and store token
    fun registerUser(username: String, email: String, password1: String, password2: String) {
        viewModelScope.launch {
            val result = authRepository.registerUser(username, email, password1, password2)
            // Handle result as needed, e.g., update UI state
        }
    }

    // Login and store token
    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            val result = authRepository.loginUser(username, password)
            // Handle result as needed, e.g., update UI state
        }
    }

    // Check if user is logged in by checking if a token exists
    suspend fun isUserLoggedIn(): Boolean {
        return tokenManager.hasAccessToken()
    }

    // Get access token directly from DataStore
    suspend fun getAccessToken(): String? {
        return tokenManager.accessTokenFlow.firstOrNull()
    }
}