package com.pwr.wanderway.presentation.routeCore.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun RouteSurface(
    title: String,
    showTopBar: Boolean = true,
    onGoBack: () -> Unit,
    content: @Composable () -> Unit
) {
    var topBarHeight by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Top Bar
        if (showTopBar) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .onGloballyPositioned { coordinates ->
                        // Capture the height of the TopBar
                        topBarHeight = coordinates.size.height
                    }
            ) {
                TopBar(title = title, onNavigationIconClick = onGoBack)
            }
        } else {
            // Reset height when top bar is hidden
            topBarHeight = 0
        }

        // Content Area
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = with(LocalDensity.current) { topBarHeight.toDp() }) // Convert height to dp
        ) {
            content()
        }
    }
}
@Preview
@Composable
fun RouteSurfacePreview() {
    AppTheme {
        RouteSurface(
            title = "Title",
            onGoBack = { }
        ) {
            LazyColumn {
                items(100) { index ->
                    Text("Item #$index", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}
