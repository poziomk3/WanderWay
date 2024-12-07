package com.pwr.wanderway.presentation.routeCore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.pwr.wanderway.R
import com.pwr.wanderway.data.model.PointOfInterest
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.Loader
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.presentation.routeCore.composable.SearchBar
import com.pwr.wanderway.presentation.routeCore.composable.SearchBarItem
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun LocationAdditionScreen(
    backNav: () -> Unit,
    routeViewModel: RouteViewModel,
) {
    val poiList by produceState<List<PointOfInterest>>(initialValue = emptyList()) {
        value = routeViewModel.loadSuggestedPOIs() ?: emptyList()
    }

    // Calculate the initial center of POIs
    val initialCenter = remember(poiList) {
        if (poiList.isNotEmpty()) {
            val avgLat = poiList.map { it.latitude }.average()
            val avgLng = poiList.map { it.longitude }.average()
            LatLng(avgLat, avgLng)
        } else {
            LatLng(51.107883, 17.038538)
        }
    }

    // Camera position state initialized with the calculated center
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialCenter, 13f) // Set default zoom level
    }

    var selectedPoi by remember { mutableStateOf<PointOfInterest?>(null) }

    var textHeight by remember { mutableIntStateOf(0) }
    LaunchedEffect(poiList) {
        if (poiList.isNotEmpty()) {
            val avgLat = poiList.map { it.latitude }.average()
            val avgLng = poiList.map { it.longitude }.average()
            cameraPositionState.animate(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(avgLat, avgLng),
                    13f
                )
            )
        }
    }

    LaunchedEffect(selectedPoi) {
        if (selectedPoi != null) {
            // Zoom into the selected POI
            cameraPositionState.animate(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(selectedPoi!!.latitude, selectedPoi!!.longitude),
                    17f // Closer zoom level for selected POI
                )
            )
        } else {
            // Reset to the default view
            cameraPositionState.animate(
                CameraUpdateFactory.newLatLngZoom(
                    initialCenter, // Center of all POIs or default center
                    13f // Default zoom level
                )
            )
        }
    }
    var searchQuery by remember { mutableStateOf("") } // SearchBar query state
    LaunchedEffect(selectedPoi) {
        searchQuery = selectedPoi?.name ?: "" // Update query to selected POI name
    }



    if (poiList.isEmpty()) {
        Loader() // Show loader if POI list is empty
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(0f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textHeight = coordinates.size.height
                        },
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.add_location_screen_description),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(
                    modifier = Modifier
                        .height(50.dp)
                        .background(Color.Red)
                )

                // Google Map
                GoogleMap(
                    modifier = Modifier
                        .shadow(8.dp)
                        .fillMaxWidth()
                        .weight(1f),
                    cameraPositionState = cameraPositionState
                ) {
                    poiList.forEach { poi ->
                        Marker(
                            state = MarkerState(
                                position = LatLng(poi.latitude, poi.longitude),
                            ),
                            title = poi.name,
                            snippet = poi.description ?: "No description available",
                            onClick = {
                                selectedPoi = poi // Update the selected POI state
                                false
                            }, icon = if (poi == selectedPoi) {
                                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                            } else {
                                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                            }
                        )
                    }
                }

                WideButton(
                    text = stringResource(id = R.string.add_location_screen_deselect_button), // Update the button label
                    onClick = {
                        selectedPoi = null // Deselect the POI
                    },
                    colorType = ButtonColor.SECONDARY // Use a secondary color for deselection
                )
                WideButton(
                    text = stringResource(id = R.string.add_location_screen_button),
                    onClick = {
                        selectedPoi?.let {
                            routeViewModel.addPointOfInterest(it)
                        }
                        backNav()
                    },
                    colorType = ButtonColor.PRIMARY
                )
            }

            // Search Bar Overlay
            Box(
                modifier = Modifier
                    .zIndex(1f)
                    .fillMaxWidth()
                    .padding(top = with(LocalDensity.current) { textHeight.toDp() })
            ) {
                SearchBar(
                    suggestions = poiList.map {
                        SearchBarItem(
                            id = it.id.toString(),
                            name = it.name,
                            additionalInfo = it.description ?: "No description"
                        )
                    },
                    onSearch = { id ->
                        val poi = poiList.find { it.id.toString() == id }
                        if (poi != null) {
                            selectedPoi = poi
                        }
                    },
                    query = searchQuery
                )
            }
        }
    }
}


@Composable
@Preview
fun LocationAdditionScreenPreview() {
    AppTheme {
        Surface {
            LocationAdditionScreen(backNav = {}, routeViewModel = viewModel())
        }
    }
}