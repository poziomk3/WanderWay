package com.pwr.wanderway.data.model

data class PointOfInterest(
    val id: Int,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
)