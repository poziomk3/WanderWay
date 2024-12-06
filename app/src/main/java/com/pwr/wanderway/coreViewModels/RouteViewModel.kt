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

    private val _routeIds = MutableStateFlow<List<Int>>(emptyList())
    val routeIds: StateFlow<List<Int>> get() = _routeIds

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    init {
        println("Initial Collected Points of Interest: ${_collectedPointsOfInterest.value}")
        observePointsOfInterest()
    }

    // Fetch POIs and populate suggestions
    fun loadPointsOfInterest() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val pois = routeRepository.getRoutePOIs() // Use result directly
                _suggestedPointsOfInterest.value = pois
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching suggested POIs: ${e.message}"
                println("Error: ${_errorMessage.value}")
            } finally {
                _loading.value = false
            }
        }
    }

    // Add a POI to the collected list
    fun addPointOfInterest(pointOfInterest: PointOfInterest) {
        _collectedPointsOfInterest.update { currentPoints ->
            currentPoints + pointOfInterest
        }
    }

    // Remove a POI from the collected list
    fun removePointOfInterest(pointOfInterest: PointOfInterest) {
        _collectedPointsOfInterest.update { currentPoints ->
            currentPoints - pointOfInterest
        }
    }

    // Reorder POIs in the collected list
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

    // Observe collected points of interest for debugging
    private fun observePointsOfInterest() {
        viewModelScope.launch {
            _collectedPointsOfInterest.collect { pointsOfInterest ->
                println("Collected Points of Interest: $pointsOfInterest")
            }
        }
    }

    // Generate routes based on collected points of interest
    fun generateRoutes() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val result = routeRepository.generateRoutes(_collectedPointsOfInterest.value)
                _routeIds.value = result
                print("Generated routes: $result")
            } catch (e: Exception) {
                _errorMessage.value = "Error generating routes: ${e.message}"
                println("Error: ${_errorMessage.value}")
            } finally {
                _loading.value = false
            }
        }
    }

    fun getRouteById(id: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val result = routeRepository.getRouteById(id)
                println("Route: $result")
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching route: ${e.message}"
                println("Error: ${_errorMessage.value}")
            } finally {
                _loading.value = false
            }
        }
    }

    // Clear error message
    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}

