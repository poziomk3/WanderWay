package com.pwr.wanderway.presentation.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Loader() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(50.dp)
            .background(Color.White),

        ) {
        CircularProgressIndicator()
    }
}