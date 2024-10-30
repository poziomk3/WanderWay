package com.pwr.wanderway.presentation.navbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthenticatedWrapper(
    viewModel: AuthenticatedWrapperViewModel,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Main content fills available space above NavBar
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)
        ) { // Reserve space for NavBar height
            content()
        }

        // NavBar pinned to the bottom
        NavBar(modifier = Modifier.align(Alignment.BottomCenter),
            onHomeClicked = { viewModel.onHomeClicked() },
            onSearchClicked = { viewModel.onSettingsClicked() }
        )
    }
}




