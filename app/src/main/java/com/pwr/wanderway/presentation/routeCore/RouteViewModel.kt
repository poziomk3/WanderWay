package com.pwr.wanderway.presentation.routeCore

import androidx.lifecycle.ViewModel
import com.pwr.wanderway.data.model.route.PointOfInterest

//@HiltViewModel
class RouteViewModel : ViewModel() {

    val collectedPointsOfInterest = mutableListOf<PointOfInterest>()

    fun addPointOfInterest(pointOfInterest: PointOfInterest) {
        collectedPointsOfInterest.add(pointOfInterest)
    }

    fun removePointOfInterest(pointOfInterest: PointOfInterest) {
        collectedPointsOfInterest.remove(pointOfInterest)
    }

    fun clearPointsOfInterest() {
        collectedPointsOfInterest.clear()
    }
}