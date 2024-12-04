package com.pwr.wanderway.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.ui.graphics.vector.ImageVector

data class RowSelectorConfig(
    val label: String,
    val icon: ImageVector = Icons.AutoMirrored.Filled.ArrowForward,
    val onClick: () -> Unit = {}
)