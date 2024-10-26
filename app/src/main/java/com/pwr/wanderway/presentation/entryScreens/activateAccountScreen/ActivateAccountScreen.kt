package com.pwr.wanderway.presentation.entryScreens.activateAccountScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.entryScreens.commons.WelcomeBackgroundWrapper
import com.pwr.wanderway.presentation.entryScreens.commons.WelcomeDialog
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun ActivateAccountScreen() {
    WelcomeBackgroundWrapper {
        WelcomeDialog(title = stringResource(id = R.string.activate_account_label), content = {
            Text(stringResource(id = R.string.activate_account_sublabel))
        }, confirmButtonText = stringResource(id = R.string.got_it))
    }
}

@Preview
@Composable
fun PreviewActivateAccountScreen() {
    AppTheme {
        ActivateAccountScreen()
    }

}