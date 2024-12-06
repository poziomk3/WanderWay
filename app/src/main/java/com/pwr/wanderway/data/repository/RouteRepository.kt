package com.pwr.wanderway.data.repository

import com.pwr.wanderway.data.local.RoutePreferencesManager
import com.pwr.wanderway.data.model.PointOfInterest
import com.pwr.wanderway.data.model.api.route.RouteGenerateRequest
import com.pwr.wanderway.network.ApiService
import javax.inject.Inject

class RouteRepository @Inject constructor(
    private val apiService: ApiService,
    private val routePreferencesManager: RoutePreferencesManager
) {
    private val _pois = mutableListOf<PointOfInterest>()
    val pois: List<PointOfInterest> get() = _pois

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

    suspend fun generateRoute(pois: List<PointOfInterest>) {
        val poiIds = pois.map { it.id }
        val requestBody = RouteGenerateRequest(poiIds, preferences = listOf())
        val response = apiService.generateRoute(requestBody)
        if (response.isSuccessful) {

        } else {
            throw Exception("Failed to generate route: ${response.errorBody()?.string()}")
        }
    }

    suspend fun getRouteById(id: Int) {
        val response = apiService.getRouteById(id)
        if (response.isSuccessful) {
        }
        else {
            throw Exception("Failed to fetch route: ${response.errorBody()?.string()}")
        }
    }
}
