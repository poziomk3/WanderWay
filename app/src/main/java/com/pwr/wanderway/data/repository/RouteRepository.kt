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


    suspend fun getRoutePOIs(): List<PointOfInterest> {
        val response = apiService.getAllPOIs()
        return if (response.isSuccessful) { // Explicitly return the result
            response.body()?.pois?.map {
                PointOfInterest(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    latitude = it.latitude,
                    longitude = it.longitude
                )
            }.orEmpty()
        } else {
            throw Exception("Failed to fetch POIs: ${response.errorBody()?.string()}")
        }
    }

    suspend fun generateRoutes(poisArg: List<PointOfInterest>): List<Int> {
        val preferences = "preferences"
        val pois = poisArg.map { it.id }

        val response = apiService.generateRoute(RouteGenerateRequest(pois, preferences))

        return if (response.isSuccessful) {
            response.body()?.routeIds.orEmpty()
        } else {
            val errorMessage = response.errorBody()?.string()
            throw Exception("Failed to fetch routes: $errorMessage")
        }
    }

    suspend fun getRouteById(id: Int): ByteArray {
        val response = apiService.getRouteById(id)
        return if (response.isSuccessful) {
            response.body()?.readBytes() ?: byteArrayOf()
        } else {
            throw Exception("Failed to fetch route: ${response.errorBody()?.string()}")
        }
    }

}
