package com.pwr.wanderway.presentation.commons

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.ui.theme.AppTheme
import androidx.compose.ui.Modifier

@Composable
fun buttonColor(colorName: String): Pair<Color, Color> {
    val colorMap = mapOf(
        "primary" to Pair(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimary),
        "secondary" to Pair(MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.onSecondary),
        "background" to Pair(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.onBackground),
        "surface" to Pair(MaterialTheme.colorScheme.surface, MaterialTheme.colorScheme.onSurface),
        "error" to Pair(MaterialTheme.colorScheme.error, MaterialTheme.colorScheme.onError),
    )
    val defaultColor = Pair(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimary)
    return colorMap[colorName] ?: defaultColor
}

@Composable
fun WideButton(
    colors: Pair<Color, Color>,
    text: String,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    androidx.compose.material3.Button(
        enabled = enabled,
        onClick = { onClick?.invoke() },
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = colors.first,
            contentColor = colors.second,
        ),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
    ) {
        androidx.compose.material3.Text(text)
    }
}

@Preview
@Composable
fun WideButtonPreview() {
    AppTheme {
        val colors = buttonColor("error")
        WideButton(colors, "Test Button")
    }
}