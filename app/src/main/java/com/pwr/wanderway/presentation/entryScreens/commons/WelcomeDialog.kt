package com.pwr.wanderway.presentation.entryScreens.commons

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.pwr.wanderway.presentation.commons.MainIcon


@Composable
fun WelcomeDialog(
    title: String,
    content: @Composable () -> Unit,
    confirmButtonText: String? = null,
    onConfirm: (() -> Unit)? = null,
    dismissButtonText: String? = null,
    onDismiss: (() -> Unit)? = null,
    onDismissed: (() -> Unit)? = null // Optional callback to notify parent on dismissal
) {
    // Internal visibility state
    var dialogVisible by remember { mutableStateOf(true) }

    if (dialogVisible) {
        AlertDialog(
            onDismissRequest = { /* Do nothing to ignore background clicks */ },
            containerColor = MaterialTheme.colorScheme.primary,
            textContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            icon = { MainIcon() },
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
                            dialogVisible = false // Hide dialog immediately
                            onConfirm?.invoke() // Invoke confirm action
                            onDismissed?.invoke() // Notify parent if needed
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
                            dialogVisible = false // Hide dialog immediately
                            onDismiss?.invoke() // Invoke dismiss action
                            onDismissed?.invoke() // Notify parent if needed
                        }
                    ) {
                        Text(dismissButtonText)
                    }
                }
            }
        )
    }
}