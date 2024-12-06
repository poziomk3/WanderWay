package com.pwr.wanderway.coreViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.data.model.PointOfInterest
import com.pwr.wanderway.data.repository.RouteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RouteViewModel @Inject constructor(
    private val routeRepository: RouteRepository
) : ViewModel() {

    private val _collectedPointsOfInterest = MutableStateFlow<List<PointOfInterest>>(emptyList())
    val collectedPointsOfInterest: StateFlow<List<PointOfInterest>> get() = _collectedPointsOfInterest

    fun addPointOfInterest(pointOfInterest: PointOfInterest) {
        _collectedPointsOfInterest.update { currentPoints ->
            currentPoints + pointOfInterest
        }
    }

    fun removePointOfInterest(pointOfInterest: PointOfInterest) {
        _collectedPointsOfInterest.update { currentPoints ->
            currentPoints - pointOfInterest
        }
    }

    fun clearPointsOfInterest() {
        _collectedPointsOfInterest.value = emptyList()
    }

    fun loadPointsOfInterest() {
        viewModelScope.launch {
            try {
                routeRepository.getRoutePOIs()
                _collectedPointsOfInterest.value = routeRepository.pois
            } catch (e: Exception) {
                // Handle the error appropriately, e.g., log or update UI
            }
        }
    }
}
