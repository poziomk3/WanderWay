package com.pwr.wanderway.utils.mappers

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import com.pwr.wanderway.data.model.PointOfInterest
import com.pwr.wanderway.presentation.commons.RowSelectorConfig

fun mapPoiToRowSelector(
    pointOfInterest: PointOfInterest,
    onClick: () -> Unit // Standard callback type for clicks
): RowSelectorConfig {
    return RowSelectorConfig(
        label = pointOfInterest.name,
        onClick = onClick,
        icon = Icons.Filled.Clear
    )
}
