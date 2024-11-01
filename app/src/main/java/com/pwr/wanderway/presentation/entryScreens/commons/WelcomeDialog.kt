package com.pwr.wanderway.presentation.entryScreens.commons

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.pwr.wanderway.presentation.commons.MainIcon


@Composable
fun WelcomeDialog(
    isDialogVisible: MutableState<Boolean>,
    title: String,
    content: @Composable () -> Unit,
    rightButton: String? = null,
    rightButtonOnClick: (() -> Unit)? = null,
    leftButton: String? = null,
    leftButtonOnClick: (() -> Unit)? = null,
) {
    if (isDialogVisible.value) {
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
                rightButton?.let {
                    TextButton(
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        onClick = {
                            rightButtonOnClick?.invoke() // Invoke confirm action
                        }
                    ) {
                        Text(rightButton)
                    }
                }
            },
            dismissButton = {
                leftButton?.let {
                    TextButton(
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        onClick = {
                            leftButtonOnClick?.invoke()
                        }
                    ) {
                        Text(leftButton)
                    }
                }
            }
        )
    }
}