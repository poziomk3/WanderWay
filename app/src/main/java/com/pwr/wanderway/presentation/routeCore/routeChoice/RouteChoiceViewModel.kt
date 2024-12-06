package com.pwr.wanderway.presentation.routeCore.routeChoice

import com.pwr.wanderway.coreViewModels.RouteViewModel

class RouteChoiceViewModel(
    private val routeViewModel: RouteViewModel
) {
    val routeIds = routeViewModel.routeIds

    fun loadRoute(id: Int) {
        routeViewModel.getRouteById(id)
    }
}
