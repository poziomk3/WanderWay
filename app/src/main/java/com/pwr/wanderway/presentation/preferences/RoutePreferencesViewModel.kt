package com.pwr.wanderway.presentation.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.data.model.preferences.RoutePreferenceCategory
import com.pwr.wanderway.data.model.preferences.RoutePreferenceOption
import com.pwr.wanderway.data.repository.RoutePreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RoutePreferencesViewModel @Inject constructor(
    private val repository: RoutePreferencesRepository
) : ViewModel() {


    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> get() = _loading.asStateFlow()

    private val _activePreferences =
        MutableStateFlow<Map<RoutePreferenceCategory, RoutePreferenceOption>>(emptyMap())
    val activePreferences: StateFlow<Map<RoutePreferenceCategory, RoutePreferenceOption>> get() = _activePreferences.asStateFlow()

    init {
        viewModelScope.launch {
            loadPreferences()
        }
    }

    private suspend fun loadPreferences() {
        _loading.value = true
        try {
            val preferences = withContext(Dispatchers.IO) {
                repository.getAllActivePreferences().first()
            }
            _activePreferences.value = preferences
        } catch (e: Exception) {
            _activePreferences.value = emptyMap() // Handle errors gracefully
        } finally {
            _loading.value = false
        }
    }


    fun savePreference(category: RoutePreferenceCategory, option: RoutePreferenceOption) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.savePreference(category, option)
            }
            loadPreferences()
        }
    }

    fun resetPreferences() {
        viewModelScope.launch {
            loadPreferences()
        }
    }
}
