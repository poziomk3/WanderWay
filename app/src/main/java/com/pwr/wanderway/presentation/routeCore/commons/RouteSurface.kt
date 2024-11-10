package com.pwr.wanderway.presentation.routeCore.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun RouteSurface(
    title: String,
    onGoBack: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.error)
        ) {
            TopBar(title = title, onNavigationIconClick = onGoBack)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 90.dp)
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
                items(100) { index -> // Example list items
                    Text("Item #$index", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}