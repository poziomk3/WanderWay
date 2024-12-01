package com.pwr.wanderway.presentation.routeCore.buildYourRoute

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun BuildYourRouteScreen(
    locationAdditionNav: () -> Unit,
    preferencesNav: () -> Unit,
    routeChoiceNav: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        WideButton(
            text = "location addition",
            onClick = { locationAdditionNav() },
            colorType = ButtonColor.PRIMARY
        )
        WideButton(
            text = "preferences",
            onClick = { preferencesNav() },
            colorType = ButtonColor.SECONDARY
        )
        WideButton(
            text = "routeChoice",
            onClick = { routeChoiceNav() },
            colorType = ButtonColor.ERROR
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