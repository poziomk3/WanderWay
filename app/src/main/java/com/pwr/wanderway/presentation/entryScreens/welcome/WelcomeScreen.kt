package com.pwr.wanderway.presentation.entryScreens.welcome

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
fun WelcomeScreen(
    viewModel: WelcomeViewModel = WelcomeViewModel(),
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    EntryScreenLayout(
        title = stringResource(id = R.string.entry_screen_title),
        content = {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(id = R.string.entry_screen_subtitle))
            }
        },
        rightButton = stringResource(id = R.string.entry_screen_login),
        leftButton = stringResource(id = R.string.entry_screen_register),
        rightButtonOnClick = { onLoginClick() },
        leftButtonOnClick = { onRegisterClick() },
    )
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    AppTheme {
        WelcomeScreen(WelcomeViewModel(), {}, {})
    }
}
