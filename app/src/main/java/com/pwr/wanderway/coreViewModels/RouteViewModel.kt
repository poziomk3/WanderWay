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

    private val _suggestedPointsOfInterest = MutableStateFlow<List<PointOfInterest>>(emptyList())
    val suggestedPointsOfInterest: StateFlow<List<PointOfInterest>> get() = _suggestedPointsOfInterest

    init {
        println("Initial Collected Points of Interest: ${_collectedPointsOfInterest.value}")
        observePointsOfInterest()
    }

    // Fetch POIs from the repository and populate suggestions
    fun loadPointsOfInterest() {
        viewModelScope.launch {
            try {
                routeRepository.getRoutePOIs()
                _suggestedPointsOfInterest.value = routeRepository.pois
            } catch (e: Exception) {
                println("Error fetching suggested POIs: ${e.message}")
            }
        }
    }

    // Stash a selected POI into collectedPointsOfInterest
    fun addPointOfInterest(pointOfInterest: PointOfInterest) {
        _collectedPointsOfInterest.update { currentPoints ->
            currentPoints + pointOfInterest
        }
    }

    // Remove a POI from collectedPointsOfInterest
    fun removePointOfInterest(pointOfInterest: PointOfInterest) {
        _collectedPointsOfInterest.update { currentPoints ->
            currentPoints - pointOfInterest
        }
    }

    // Reorder POIs in collectedPointsOfInterest
    fun reorderPointsOfInterest(fromIndex: Int, toIndex: Int) {
        _collectedPointsOfInterest.update { currentPoints ->
            val mutableList = currentPoints.toMutableList()
            val item = mutableList.removeAt(fromIndex)
            mutableList.add(toIndex, item)
            mutableList
        }
    }

    // Clear all collected POIs
    fun clearPointsOfInterest() {
        _collectedPointsOfInterest.value = emptyList()
    }

    private fun observePointsOfInterest() {
        viewModelScope.launch {
            _collectedPointsOfInterest.collect { pointsOfInterest ->
                println("Collected Points of Interest: $pointsOfInterest")
            }
        }
    }
}
