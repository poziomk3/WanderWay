package com.pwr.wanderway.data.model.route

data class PointOfInterest(
    val id: Int,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
)