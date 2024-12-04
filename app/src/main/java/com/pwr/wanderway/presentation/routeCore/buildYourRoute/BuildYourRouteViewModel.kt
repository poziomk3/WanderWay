package com.pwr.wanderway.presentation.routeCore.buildYourRoute

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.lifecycle.ViewModel
import com.pwr.wanderway.data.model.RowSelectorConfig

class BuildYourRouteViewModel: ViewModel(){
    // create list of example attractions, with callback which removes the composable onCLick
    val unseenDestinations = listOf(
        RowSelectorConfig(
            label = "Ogród japoński",
            icon = Icons.Filled.Clear,
            onClick = { }
        ),
        RowSelectorConfig(
            label = "Panorama Racławicka",
            icon = Icons.Filled.Clear,
            onClick = { }
        ),
        RowSelectorConfig(
            label = "Wieża Matematyczna",
            icon = Icons.Filled.Clear,
            onClick = { }
        ),
        RowSelectorConfig(
            label = "Muzeum Narodowe",
            icon = Icons.Filled.Clear,
            onClick = { }
        ),
        RowSelectorConfig(
            label = "Muzeum Neonów",
            icon = Icons.Filled.Clear,
            onClick = { }
        ),
    )
}