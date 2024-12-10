package com.pwr.wanderway.presentation.routeCore.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.R
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun GoToForumDialog(
    goToForum: () -> Unit,
    close: () -> Unit,
) {
    DialogWrapper(onDismissRequest = {}) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = stringResource(R.string.route_display_screen_rate_this_route),
            )
            Row (
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ){
                TextButton(
                    onClick = { close() }
                ) {
                    Text(
                        text = stringResource(R.string.route_display_screen_dismiss_dialog)
                    )
                }
                TextButton(
                    onClick = { goToForum() }
                ) {
                    Text(
                        text = stringResource(R.string.route_display_screen_go_to_forum)
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun GoToForumDialogPreview() {
    AppTheme {
        GoToForumDialog(goToForum = {}, close = {})
    }
}