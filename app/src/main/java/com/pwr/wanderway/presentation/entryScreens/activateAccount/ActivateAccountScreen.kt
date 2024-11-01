package com.pwr.wanderway.presentation.entryScreens.activateAccount

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.entryScreens.commons.EntryScreenLayout
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun ActivateAccountScreen(
    viewModel: ActivateAccountViewModel = ActivateAccountViewModel(),
    onSuccess: () -> Unit
) {
    EntryScreenLayout(
        title = stringResource(id = R.string.activate_account_label),
        content = {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    stringResource(id = R.string.activate_account_sublabel)
                )
            }
        },
        rightButton = stringResource(id = R.string.got_it),
        rightButtonOnClick = { onSuccess() })
}

@Preview
@Composable
fun ActivateAccountScreenPreview() {
    AppTheme {
        ActivateAccountScreen(ActivateAccountViewModel(), {})
    }
}