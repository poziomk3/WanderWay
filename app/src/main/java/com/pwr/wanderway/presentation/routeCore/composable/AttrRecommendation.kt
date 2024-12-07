package com.pwr.wanderway.presentation.routeCore.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.ui.theme.AppTheme
import androidx.compose.ui.Modifier

@Composable
fun AttrRecommendation(
    text: String,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium
        )
        Icon(
            modifier = Modifier.clickable { onClick?.invoke() },
            imageVector = Icons.Default.Clear,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun AttrRecommendationPreview() {
    AppTheme {
        AttrRecommendation(
            text = "Hala Stulecia"
        )
    }
}
