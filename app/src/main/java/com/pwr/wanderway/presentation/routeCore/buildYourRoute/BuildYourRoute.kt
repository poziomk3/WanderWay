package com.pwr.wanderway.presentation.routeCore.buildYourRoute

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pwr.wanderway.data.model.RowSelectorConfig
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.presentation.routeCore.commons.DestInfo
import com.pwr.wanderway.presentation.routeCore.commons.RowSelector
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun BuildYourRouteScreen(
    buildYourRouteViewModel: BuildYourRouteViewModel = viewModel(),
    locationAdditionNav: () -> Unit,
    preferencesNav: () -> Unit,
    routeChoiceNav: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        RowSelector(
            config = RowSelectorConfig(
                text = "Add starting point",
                onClick = locationAdditionNav
            )
        )
        RowSelector(
            config = RowSelectorConfig(
                text = "Add destination",
                onClick = { }
            )
        )
        WideButton(
            text = "Change your personal preferences",
            onClick = { preferencesNav() },
            colorType = ButtonColor.SECONDARY
        )
        WideButton(
            text = "Create route",
            onClick = routeChoiceNav,
            colorType = ButtonColor.PRIMARY
        )
        Text(
            text = "Trip overview",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold)
        )
        DestInfo(
            label = "Starting in:",
            contents = { Text(text = "Location 1") }
        )
        DestInfo(
            label = "Finishing in:",
            contents = { Text(text = "Destination 1, Destination 2") }
        )
        DestInfo(
            label = "Attractions not seen yet:",
            contents = {
                LazyColumn {
                    items(buildYourRouteViewModel.unseenDestinations) { config ->
                        RowSelector(config = config)
                    }
                }
            }
        )

    }
}

@Preview
@Composable
fun BuildYourRouteScreenPreview() {
    AppTheme {
        BuildYourRouteScreen(
            locationAdditionNav = {},
            preferencesNav = {},
            routeChoiceNav = { })
    }
}