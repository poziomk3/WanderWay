package com.pwr.wanderway.presentation.entryScreens.commons

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.pwr.wanderway.presentation.commons.MainIcon


@Composable
fun WelcomeDialog(
    title: String,
    content: @Composable () -> Unit,
    confirmButtonText: String? = null,
    onConfirm: (() -> Unit)? = null,
    dismissButtonText: String? = null,
    onDismiss: (() -> Unit)? = null
) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.primary,
        textContentColor = MaterialTheme.colorScheme.onPrimary,
        titleContentColor = MaterialTheme.colorScheme.onPrimary,
        icon = { MainIcon() },
        onDismissRequest = { onDismiss?.invoke() },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            content()
        },
        confirmButton = {
            confirmButtonText?.let {
                TextButton(
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    onClick = {
                        onConfirm?.invoke()
                    }
                ) {
                    Text(confirmButtonText)
                }
            }
        },
        dismissButton = {
            dismissButtonText?.let {
                TextButton(
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    onClick = {
                        onDismiss?.invoke()
                    }
                ) {
                    Text(dismissButtonText)
                }
            }
        }
    )
}