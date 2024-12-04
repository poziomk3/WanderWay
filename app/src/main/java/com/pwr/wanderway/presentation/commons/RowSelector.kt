package com.pwr.wanderway.presentation.commons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.data.model.RowSelectorConfig
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun RowSelector(
    config: RowSelectorConfig
) {
    val (label, icon, onClick) = config

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick.invoke() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
        Icon(
            imageVector = icon,
            contentDescription = label
        )
    }
}

@Preview
@Composable
fun RowSelectorPreview() {
    AppTheme {
        RowSelector(
            config = RowSelectorConfig(
                label = "Row Selector",
                icon = Icons.AutoMirrored.Filled.ArrowForward
            )
        )
    }
}
