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
import androidx.hilt.navigation.compose.hiltViewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.coreViewModels.RouteViewModel
import com.pwr.wanderway.presentation.routeCore.commons.MapComponent.MapComponent
import com.pwr.wanderway.presentation.routeCore.commons.SearchBar
import com.pwr.wanderway.presentation.routeCore.commons.SearchBarItem
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun LocationAdditionScreen(backNav: () -> Unit,routeViewModel: RouteViewModel = hiltViewModel()) {
    val poiList by routeViewModel.collectedPointsOfInterest.collectAsState(initial = emptyList())

    // Fetch the POIs when the screen loads
    LaunchedEffect(Unit) {
        routeViewModel.loadPointsOfInterest() // Use your desired routeId
    }
    LaunchedEffect(poiList) {
        Log.d("LocationAdditionScreen", "POI list: $poiList")
    }
    val suggestionList = listOf(
        SearchBarItem(id = "1", name = "Apple", additionalInfo = "Fruit"),
        SearchBarItem(id = "2", name = "Banana", additionalInfo = "Yellow fruit"),
        SearchBarItem(id = "3", name = "Cherry", additionalInfo = "Small red fruit"),
        SearchBarItem(id = "4", name = "Date", additionalInfo = "Sweet brown fruit"),
        SearchBarItem(id = "5", name = "Elderberry", additionalInfo = "Dark purple berry"),
        SearchBarItem(id = "6", name = "Fig", additionalInfo = "Soft and sweet"),
        SearchBarItem(id = "7", name = "Grape", additionalInfo = "Available in bunches"),
        SearchBarItem(id = "8", name = "Honeydew", additionalInfo = "Green melon"),
        SearchBarItem(id = "9", name = "Kiwi", additionalInfo = "Fuzzy brown exterior"),
        SearchBarItem(id = "10", name = "Lemon", additionalInfo = "Sour yellow citrus")
    )

    var textHeight by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Main content column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(0f), // Base layer
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

            // Reserve space for the search bar
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

        // Overlay the search bar on top of content
        Box(
            modifier = Modifier
                .zIndex(1f)
                .fillMaxWidth()
                .padding(top = with(LocalDensity.current) { textHeight.toDp() }) // Use dynamic height
        ) {
            SearchBar(
                suggestions = suggestionList,
                onSearch = { id ->
                    println("Selected item ID: $id")
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
            LocationAdditionScreen(backNav = {},routeViewModel = hiltViewModel())
        }
    }
}