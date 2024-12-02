package com.pwr.wanderway.presentation.routeCore.buildYourRoute

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.lifecycle.ViewModel
import com.pwr.wanderway.data.model.RowSelectorConfig

class BuildYourRouteViewModel: ViewModel(){
    // create list of example attractions, with callback which removes the composable onCLick
    val unseenDestinations = listOf(
        RowSelectorConfig(
            text = "Ogród japoński",
            icon = Icons.Filled.Clear,
            onClick = { }
        ),
        RowSelectorConfig(
            text = "Panorama Racławicka",
            icon = Icons.Filled.Clear,
            onClick = { }
        ),
        RowSelectorConfig(
            text = "Wieża Matematyczna",
            icon = Icons.Filled.Clear,
            onClick = { }
        ),
        RowSelectorConfig(
            text = "Muzeum Narodowe",
            icon = Icons.Filled.Clear,
            onClick = { }
        ),
        RowSelectorConfig(
            text = "Muzeum Neonów",
            icon = Icons.Filled.Clear,
            onClick = { }
        ),
    )
}