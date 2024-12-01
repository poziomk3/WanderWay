package com.pwr.wanderway.presentation.routeCore.commons.Map

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Data class to hold latitude and longitude
data class LocationState(val latitude: Double, val longitude: Double)

class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    private val _currentLocation = MutableStateFlow<LocationState?>(null)
    val currentLocation: StateFlow<LocationState?> = _currentLocation

    /**
     * Fetches the last known location. Assumes permissions are already granted.
     */
    @SuppressLint("MissingPermission") // Permissions are handled by the Composable
    fun fetchLastLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    _currentLocation.value = LocationState(location.latitude, location.longitude)
                } else {
                    _currentLocation.value = null // No last location available
                }
            }
            .addOnFailureListener { exception ->
                _currentLocation.value = null // Handle failure case
                exception.printStackTrace() // Log exception for debugging
            }
    }
}
