package com.pwr.wanderway.coreViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.data.model.preferences.PreferenceCategory
import com.pwr.wanderway.data.model.preferences.PreferenceOption
import com.pwr.wanderway.data.repository.RoutePreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val repository: RoutePreferencesRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(true) // Initial loading state
    val loading: StateFlow<Boolean> get() = _loading.asStateFlow()

    private val _preferences = repository.getAllActivePreferences()
        .onStart { _loading.value = true }
        .map { preferences ->
            _loading.value = false // Mark as loaded when flow emits
            preferences
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyMap()) // Ensure initial state

    fun getAllActivePreferences(): StateFlow<Map<PreferenceCategory, PreferenceOption>> {
        return _preferences
    }

    fun savePreference(category: PreferenceCategory, option: PreferenceOption) {
        viewModelScope.launch {
            repository.savePreference(category, option)
        }
    }
}

