package com.pwr.wanderway.presentation.routeCore.locationAddition

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.coreViewModels.RouteViewModel
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.presentation.routeCore.commons.MapComponent.MapComponent
import com.pwr.wanderway.presentation.routeCore.commons.SearchBar
import com.pwr.wanderway.presentation.routeCore.commons.SearchBarItem
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun LocationAdditionScreen(
    backNav: () -> Unit,
    routeViewModel: RouteViewModel,
    locationViewModel: LocationAdditionViewModel = LocationAdditionViewModel(routeViewModel)
) {
    val poiList by locationViewModel.suggestedPointsOfInterest.collectAsState(initial = emptyList())

    // Fetch the POIs when the screen loads
    LaunchedEffect(Unit) {
        locationViewModel.loadLocations()
    }
    var textHeight by remember { mutableIntStateOf(0) }
    LaunchedEffect(poiList) {
        Log.d("LocationAdditionScreen", "POI list: $poiList")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(0f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textHeight = coordinates.size.height // Capture the height of the Text
                    },
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.add_location_screen_description),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(
                modifier = Modifier
                    .height(50.dp)
                    .background(Color.Red)
            )

            MapComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            WideButton(
                text = stringResource(id = R.string.add_location_screen_button),
                onClick = { backNav() },
                colorType = ButtonColor.PRIMARY
            )
        }

        Box(
            modifier = Modifier
                .zIndex(1f)
                .fillMaxWidth()
                .padding(top = with(LocalDensity.current) { textHeight.toDp() })
        ) {
            SearchBar(
                suggestions = poiList.map {
                    SearchBarItem(
                        id = it.id.toString(),
                        name = it.name,
                        additionalInfo = it.description
                    )
                },
                onSearch = { id ->
                    val poi = poiList.find { it.id.toString() == id }
                    if (poi != null) {
                        locationViewModel.addLocation(poi)
                    }
                }
            )
        }
    }
}


@Composable
@Preview
fun LocationAdditionScreenPreview() {
    AppTheme {
        Surface {
            LocationAdditionScreen(backNav = {}, routeViewModel = viewModel())
        }
    }
}