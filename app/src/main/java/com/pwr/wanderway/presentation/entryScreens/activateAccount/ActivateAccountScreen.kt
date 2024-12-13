package com.pwr.wanderway.presentation.entryScreens.activateAccount

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.entryScreens.commons.EntryScreenLayout
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun ActivateAccountScreen(
    onSuccess: () -> Unit
) {
    EntryScreenLayout(
        title = stringResource(id = R.string.entry_screen_title_activate_account),
        content = {
            Text(
                stringResource(id = R.string.entry_screen_subtitle_activate_account),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        },
        rightButton = stringResource(id = R.string.entry_screen_button_ok),
        rightButtonOnClick = { onSuccess() })
}

@Preview
@Composable
fun ActivateAccountScreenPreview() {
    AppTheme {
        ActivateAccountScreen({})
    }
}