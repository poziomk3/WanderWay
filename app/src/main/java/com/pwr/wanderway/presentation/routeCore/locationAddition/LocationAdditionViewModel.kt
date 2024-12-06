package com.pwr.wanderway.presentation.routeCore.locationAddition

import androidx.lifecycle.ViewModel
import com.pwr.wanderway.coreViewModels.RouteViewModel
import com.pwr.wanderway.data.model.PointOfInterest

class LocationAdditionViewModel (
    private val routeViewModel: RouteViewModel
) : ViewModel() {

    val suggestedPointsOfInterest = routeViewModel.suggestedPointsOfInterest

    fun addLocation(pointOfInterest: PointOfInterest) {
        routeViewModel.addPointOfInterest(pointOfInterest)
    }

    fun removeLocation(pointOfInterest: PointOfInterest) {
        routeViewModel.removePointOfInterest(pointOfInterest)
    }

    fun clearLocations() {
        routeViewModel.clearPointsOfInterest()
    }

    fun loadLocations() {
        routeViewModel.loadPointsOfInterest()
    }
}