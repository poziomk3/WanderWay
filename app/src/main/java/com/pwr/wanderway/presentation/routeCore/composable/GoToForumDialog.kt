package com.pwr.wanderway.presentation.routeCore.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GoToForumDialog(
    goToForum: () -> Unit,
) {
    DialogWrapper(onDismissRequest = {}) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Rate our route on the forum!",
            )
            TextButton(
                onClick = { goToForum() }
            ) {
                Text(
                    text = "Go to forum"
                )
            }
        }
    }
}