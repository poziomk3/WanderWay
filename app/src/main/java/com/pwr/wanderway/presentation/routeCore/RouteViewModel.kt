package com.pwr.wanderway.presentation.routeCore

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.pwr.wanderway.data.model.PointOfInterest
import com.pwr.wanderway.data.repository.RouteRepository
import com.pwr.wanderway.utils.gpx.openGoogleMapsWithWaypoints
import com.pwr.wanderway.utils.gpx.parseGpxFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RouteViewModel @Inject constructor(
    private val routeRepository: RouteRepository
) : ViewModel() {

    private val _collectedPointsOfInterest = MutableStateFlow<List<PointOfInterest>>(emptyList())
    val collectedPointsOfInterest: StateFlow<List<PointOfInterest>> get() = _collectedPointsOfInterest

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    suspend fun loadSuggestedPOIs(): List<PointOfInterest>? {
        _loading.value = true
        return try {
            routeRepository.getRoutePOIs()
        } catch (exception: Exception) {
            println("Error fetching suggested POIs: ${exception.message}")
            null
        } finally {
            _loading.value = false
        }
    }

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

    fun emptyPointsOfInterest() {
        _collectedPointsOfInterest.value = emptyList()
    }

    fun reorderPointsOfInterest(fromIndex: Int, toIndex: Int) {
        _collectedPointsOfInterest.update { currentPoints ->
            val mutableList = currentPoints.toMutableList()
            val item = mutableList.removeAt(fromIndex)
            mutableList.add(toIndex, item)
            mutableList
        }
    }

    suspend fun generateRoutes(): List<Int>? {
        _loading.value = true
        return try {
            routeRepository.generateRoutes(_collectedPointsOfInterest.value)
        } catch (e: Exception) {
            println("Error generating routes: ${e.message}")
            null
        } finally {
            _loading.value = false
        }
    }


    suspend fun redirectToGoogleMaps(routeId: Int, context: Context) {
        _loading.value = true
        try {
            val inputStream = routeRepository.getRouteById(routeId)

            val waypoints = parseGpxFile(inputStream)
            openGoogleMapsWithWaypoints(context, waypoints)
        } catch (e: Exception) {
            Toast.makeText(context, "Failed to process route: ${e.message}", Toast.LENGTH_LONG)
                .show()
        } finally {
            _loading.value = false
        }
    }
}


