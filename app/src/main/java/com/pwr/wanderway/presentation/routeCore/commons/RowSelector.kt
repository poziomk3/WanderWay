package com.pwr.wanderway.presentation.routeCore.commons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.ui.theme.AppTheme
import androidx.compose.ui.Modifier
import com.pwr.wanderway.data.model.RowSelectorConfig

@Composable
fun RowSelector(
    config: RowSelectorConfig = RowSelectorConfig()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { config.onClick.invoke() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = config.text,
            style = MaterialTheme.typography.bodySmall
        )
        Icon(
            imageVector = config.icon,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun RowSelectorPreview() {
    AppTheme {
        RowSelector()
    }
}
