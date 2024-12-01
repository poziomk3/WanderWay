package com.pwr.wanderway.presentation.routeCore.buildYourRoute

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.lifecycle.ViewModel
import com.pwr.wanderway.data.model.RowSelectorConfig

class BuildYourRouteViewModel: ViewModel(){
    // create list of example attractions, with callback which removes the composable onCLick
    val unseenDestinations = listOf(
        RowSelectorConfig(
            text = "Destination 1",
            icon = Icons.Filled.Clear,
            onClick = { }
        ),
        RowSelectorConfig(
            text = "Destination 2",
            icon = Icons.Filled.Clear,
            onClick = { }
        ),
        RowSelectorConfig(
            text = "Destination 3",
            icon = Icons.Filled.Clear,
            onClick = { }
        ),
        RowSelectorConfig(
            text = "Destination 4",
            icon = Icons.Filled.Clear,
            onClick = { }
        ),
        RowSelectorConfig(
            text = "Destination 5",
            icon = Icons.Filled.Clear,
            onClick = { }
        ),
    )
}