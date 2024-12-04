package com.pwr.wanderway.presentation.commons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.ui.theme.AppTheme

enum class ButtonColor {
    PRIMARY,
    SECONDARY,
    BACKGROUND,
    SURFACE,
    ERROR
}

@Composable
fun buttonColor(colorType: ButtonColor): Pair<Color, Color> {
    return when (colorType) {
        ButtonColor.PRIMARY -> Pair(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.onPrimary
        )

        ButtonColor.SECONDARY -> Pair(
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.onSecondary
        )

        ButtonColor.BACKGROUND -> Pair(
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.onBackground
        )

        ButtonColor.SURFACE -> Pair(
            MaterialTheme.colorScheme.surface,
            MaterialTheme.colorScheme.onSurface
        )

        ButtonColor.ERROR -> Pair(
            MaterialTheme.colorScheme.error,
            MaterialTheme.colorScheme.onError
        )
    }
}

@Composable
fun WideButton(
    colorType: ButtonColor,
    text: String,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    val colors = buttonColor(colorType)
    ElevatedButton(
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 16.dp
        ),
        enabled = enabled,
        onClick = { onClick?.invoke() },
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = colors.first,
            contentColor = colors.second,
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview
@Composable
fun WideButtonPreview() {
    AppTheme {
        WideButton(ButtonColor.PRIMARY, "Test Button")
    }
}