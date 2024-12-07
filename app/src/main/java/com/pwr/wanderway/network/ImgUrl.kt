package com.pwr.wanderway.network

import com.pwr.wanderway.BuildConfig

object ImgUrl {
    fun getRouteImg(routeId: Int, type: RouteImageType): String {
        return "${BuildConfig.BACKEND_URL}/route/$routeId/img?imgtype=${type.type}"
    }

    fun getPOIImg(poiId: Int): String {
        return "${BuildConfig.BACKEND_URL}/poi/$poiId/img"
    }
}

enum class RouteImageType(val type: String) {
    HYBRID("hybrid"),
    SATELLITE("satellite"),
    ROADMAP("roadmap"),
    TERRAIN("terrain")
}