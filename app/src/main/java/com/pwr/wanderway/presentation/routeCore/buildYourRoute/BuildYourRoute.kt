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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pwr.wanderway.R
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
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        RowSelector(
            config = RowSelectorConfig(
                text = stringResource(R.string.add_starting_point),
                onClick = locationAdditionNav
            )
        )
        RowSelector(
            config = RowSelectorConfig(
                text = stringResource(R.string.add_destination),
                onClick = { }
            )
        )
        WideButton(
            text = stringResource(R.string.change_your_personal_preferences),
            onClick = { preferencesNav() },
            colorType = ButtonColor.SECONDARY
        )
        WideButton(
            text = stringResource(R.string.create_route),
            onClick = routeChoiceNav,
            colorType = ButtonColor.PRIMARY
        )
        Text(
            text = stringResource(R.string.trip_overview),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold)
        )
        DestInfo(
            label = stringResource(R.string.starting_in),
            contents = { Text(text = "-") }
        )
        DestInfo(
            label = stringResource(R.string.finishing_in),
            contents = { Text(text = "-") }
        )
        DestInfo(
            label = stringResource(R.string.attractions_not_seen_yet),
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