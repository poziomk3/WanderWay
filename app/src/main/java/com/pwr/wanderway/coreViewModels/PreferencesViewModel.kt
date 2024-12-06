package com.pwr.wanderway.coreViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.data.model.preferences.PreferenceCategory
import com.pwr.wanderway.data.model.preferences.PreferenceOption
import com.pwr.wanderway.data.repository.RoutePreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val repository: RoutePreferencesRepository
) : ViewModel() {

    // Get the current selected option for a specific category
    fun getActivePreferenceFlow(category: PreferenceCategory): Flow<PreferenceOption> {
        return repository.getActivePreferenceFlow(category)
    }

    // Save the selected option for a specific category
    fun savePreference(category: PreferenceCategory, selectedOption: PreferenceOption) {
        viewModelScope.launch {
            repository.savePreference(category, selectedOption)
        }
    }

    // Get all active preferences as a map
    fun getAllActivePreferences(): Flow<Map<PreferenceCategory, PreferenceOption>> {
        return repository.getAllActivePreferences()
    }
}
