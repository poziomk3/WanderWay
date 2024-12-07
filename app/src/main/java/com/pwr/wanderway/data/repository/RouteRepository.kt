package com.pwr.wanderway.data.repository

import com.pwr.wanderway.data.local.RoutePreferencesManager
import com.pwr.wanderway.data.model.PointOfInterest
import com.pwr.wanderway.data.model.api.route.RouteGenerateRequest
import com.pwr.wanderway.network.ApiService
import java.io.InputStream
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

    suspend fun getRouteById(id: Int): InputStream {
        val response = apiService.getRouteById(id)
        if (response.isSuccessful) {
            val responseBody = response.body()
            return responseBody?.byteStream() ?: throw Exception("Empty GPX file from backend")
        } else {
            throw Exception("Failed to fetch GPX file: ${response.errorBody()?.string()}")
        }
    }

}
