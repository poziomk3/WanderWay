package com.pwr.wanderway.presentation.routeCore.composable.MapComponent

import android.Manifest
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapComponent(
    locationViewModel: LocationViewModel = viewModel(),
    defaultLocation: LatLng = LatLng(52.191097, 19.355406),
    myLocation: Boolean = true,
    myLocationButton: Boolean = false,
    zoomControls: Boolean = false,
    modifier: Modifier = Modifier
) {
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

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 5.5f)
    }

    currentLocation?.let { location ->
        cameraPositionState.position =
            CameraPosition.fromLatLngZoom(LatLng(location.latitude, location.longitude), 15f)
    }

    GoogleMap(
        cameraPositionState = cameraPositionState,
        modifier = modifier.shadow(8.dp),
        uiSettings = MapUiSettings(
            myLocationButtonEnabled = myLocationButton,
            zoomControlsEnabled = zoomControls
        ),
        properties = MapProperties(isMyLocationEnabled = myLocation),
    )
}
