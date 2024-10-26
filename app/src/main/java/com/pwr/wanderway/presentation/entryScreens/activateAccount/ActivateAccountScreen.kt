package com.pwr.wanderway.presentation.entryScreens.activateAccount

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.entryScreens.commons.WelcomeBackgroundWrapper
import com.pwr.wanderway.presentation.entryScreens.commons.WelcomeDialog

@Composable
fun ActivateAccountScreen(
    viewModel: ActivateAccountViewModel
) {
    WelcomeBackgroundWrapper {
        WelcomeDialog(
            title = stringResource(id = R.string.activate_account_label),
            content = {
                Text(stringResource(id = R.string.activate_account_sublabel))
            },
            confirmButtonText = stringResource(id = R.string.got_it),
            onConfirm = { viewModel.onGotItClicked() })
    }
}

