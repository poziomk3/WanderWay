package com.pwr.wanderway.presentation.routeCore.buildYourRoute

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun BuildYourRouteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text("essa")
    }
}

@Preview
@Composable
fun BuildYourRouteScreenPreview() {
    AppTheme {
        Surface {
            BuildYourRouteScreen()
        }
    }
}