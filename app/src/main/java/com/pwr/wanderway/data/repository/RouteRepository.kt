package com.pwr.wanderway.data.repository

import com.pwr.wanderway.data.local.RoutePreferencesManager
import com.pwr.wanderway.data.model.PointOfInterest
import com.pwr.wanderway.network.ApiService
import javax.inject.Inject

class RouteRepository @Inject constructor(
    private val apiService: ApiService,
    private val routePreferencesManager: RoutePreferencesManager
) {
    private val _pois = mutableListOf<PointOfInterest>()
    val pois: List<PointOfInterest> get() = _pois

    private val _collectedPOIs = mutableListOf<PointOfInterest>()
    val collectedPOIs: List<PointOfInterest> get() = _collectedPOIs

    // Fetch POIs from API and update the local list
    suspend fun getRoutePOIs() {
        val response = apiService.getAllPOIs()
        if (response.isSuccessful) {
            _pois.clear()
            _pois.addAll(response.body()?.pois?.map {
                PointOfInterest(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    latitude = it.latitude,
                    longitude = it.longitude
                )
            }.orEmpty())
        } else {
            throw Exception("Failed to fetch POIs: ${response.errorBody()?.string()}")
        }
    }

    // Save a collected POI
    fun saveCollectedPOI(poi: PointOfInterest) {
        _collectedPOIs.add(poi)
    }

    // Remove a collected POI
    fun deleteCollectedPOI(poi: PointOfInterest) {
        _collectedPOIs.remove(poi)
    }

    // Clear all collected POIs
    fun clearCollectedPOIs() {
        _collectedPOIs.clear()
    }
}
