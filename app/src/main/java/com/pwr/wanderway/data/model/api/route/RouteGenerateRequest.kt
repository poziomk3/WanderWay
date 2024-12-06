package com.pwr.wanderway.data.model.api.route

data class RouteGenerateRequest(
    val pois: List<Int>,
    val preferences: List<String>
)
