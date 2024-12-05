package com.pwr.wanderway.data.repository

import com.pwr.wanderway.data.model.route.PointOfInterest
import com.pwr.wanderway.network.ApiService
import javax.inject.Inject

class RouteRepository @Inject constructor(
    private val apiService: ApiService
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
}
