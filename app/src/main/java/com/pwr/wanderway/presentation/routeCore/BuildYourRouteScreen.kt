package com.pwr.wanderway.presentation.routeCore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.RowSelector
import com.pwr.wanderway.presentation.commons.RowSelectorConfig
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.presentation.routeCore.composable.DestInfo
import com.pwr.wanderway.ui.theme.AppTheme
import com.pwr.wanderway.utils.mappers.mapPoiToRowSelector

@Composable
fun BuildYourRouteScreen(
    locationAdditionNav: () -> Unit,
    preferencesNav: () -> Unit,
    routeChoiceNav: () -> Unit,
    routeViewModel: RouteViewModel,
) {
    val collectedPointsOfInterest by routeViewModel.collectedPointsOfInterest.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Add destination button
        RowSelector(
            config = RowSelectorConfig(
                label = stringResource(R.string.build_your_route_screen_add_destination),
                onClick = locationAdditionNav
            )
        )

        // Personal preferences button
        WideButton(
            text = stringResource(R.string.build_your_route_screen_change_your_personal_preferences),
            onClick = preferencesNav,
            colorType = ButtonColor.SECONDARY
        )

        // Create route button
        WideButton(
            text = stringResource(R.string.build_your_route_screen_create_route),
            onClick = {
                routeChoiceNav()
            },
            colorType = ButtonColor.PRIMARY
        )

        // Trip overview header
        Text(
            text = stringResource(R.string.build_your_route_screen_trip_overview),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold)
        )

        // List of points of interest
        DestInfo(
            label = if (collectedPointsOfInterest.isNotEmpty()) {
                stringResource(R.string.build_your_route_screen_attractions_in_route)
            } else {
                ""
            },
            contents = {
                LazyColumn(
                ) {
                    items(collectedPointsOfInterest) { config ->
                        RowSelector(
                            config = mapPoiToRowSelector(
                                config
                            ) { routeViewModel.removePointOfInterest(config) }
                        )
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
            routeChoiceNav = { },
            routeViewModel = hiltViewModel()
        )
    }
}