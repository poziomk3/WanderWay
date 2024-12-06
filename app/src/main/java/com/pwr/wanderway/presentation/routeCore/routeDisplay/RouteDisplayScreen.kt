package com.pwr.wanderway.presentation.routeCore.routeDisplay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.presentation.routeCore.commons.InfoRow
import com.pwr.wanderway.presentation.routeCore.commons.InfoRowData
import com.pwr.wanderway.presentation.routeCore.commons.MapComponent.MapComponent
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun RouteDisplayScreen(
    buildYourOwnRouteNav: () -> Unit,
    locationAdditionNav: () -> Unit,
    routeId: String
) {

val rows = listOf(
    InfoRowData(label = "Elapsed time:", value = "00:15:13"),
    InfoRowData(label = "Distance:", value = "1500m / 5700 steps"),
    InfoRowData(label = "Progress:", value = "13 places visited out of 24 given"),
    InfoRowData(label = "Estimated finish in:", value = "23 minutes")
)
Column(
modifier = Modifier
.fillMaxSize()
.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
) {
    Text(
        text = "Route $routeId",
    )
    MapComponent(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
    )
    Column {
        // Each row of the card
        rows.forEach { row ->
            InfoRow(row)
        }
    }
    WideButton(
        text = stringResource(id = R.string.route_display_screen_button_1),
        onClick = { buildYourOwnRouteNav() },
        colorType = ButtonColor.SECONDARY
    )
    WideButton(
        text = stringResource(id = R.string.route_display_screen_button_2),
        onClick = { locationAdditionNav() },
        colorType = ButtonColor.PRIMARY
    )
}
}

@Preview
@Composable
fun RouteDisplayScreenPreview() {
    AppTheme {
        Surface {
            RouteDisplayScreen({}, {}, routeId = "1")
        }
    }
}