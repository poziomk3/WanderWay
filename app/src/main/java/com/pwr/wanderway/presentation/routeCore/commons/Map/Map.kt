package com.pwr.wanderway.presentation.routeCore.commons.Map

import android.Manifest
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Map(locationViewModel: LocationViewModel = viewModel()) {
    // Manage permission state
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    // Observe location state from the ViewModel
    val currentLocation by locationViewModel.currentLocation.collectAsState()

    // Trigger fetching the location when permission is granted
    LaunchedEffect(locationPermissionState.status.isGranted) {
        if (locationPermissionState.status.isGranted) {
            locationViewModel.fetchLastLocation()
        }
    }

    // UI when permission is not granted
    if (!locationPermissionState.status.isGranted) {
        // Ask for the permission
        LaunchedEffect(Unit) {
            locationPermissionState.launchPermissionRequest()
        }
        Text("Please grant location permissions to use the map.")
        return
    }

    // Show the location or a fallback message
    currentLocation?.let { location ->
        val latLng = LatLng(location.latitude, location.longitude)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(latLng, 15f)
        }

        // Replace the Text composable with GoogleMap when using Maps Compose
//        GoogleMap(
//            modifier = Modifier.fillMaxSize(),
//            cameraPositionState = cameraPositionState
//        )
        Text("Location: Latitude = ${latLng.latitude}, Longitude = ${latLng.longitude}")
    } ?: run {
        Text("Fetching location or no location available.")
    }
}
