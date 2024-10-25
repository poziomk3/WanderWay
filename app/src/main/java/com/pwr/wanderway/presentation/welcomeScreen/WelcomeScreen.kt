package com.pwr.wanderway.presentation.welcomeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.WelcomeBackgroundWrapper
import com.pwr.wanderway.presentation.commons.WelcomeDialog
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun WelcomeScreen() {
    WelcomeBackgroundWrapper {
        WelcomeDialog(
            title = stringResource(id = R.string.welcome_label), content = {
                Box(
                    modifier = Modifier.fillMaxWidth(), // Ensures Box takes the full width of the dialog
                    contentAlignment = Alignment.Center // Centers the content inside the Box
                ) {
                    Text(stringResource(id = R.string.welcome_sublabel))
                }
            },
            confirmButtonText = stringResource(id = R.string.login),
            dismissButtonText = stringResource(id = R.string.register)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    AppTheme {
        WelcomeScreen()
    }
}
