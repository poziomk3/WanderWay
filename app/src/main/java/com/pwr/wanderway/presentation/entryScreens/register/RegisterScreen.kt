package com.pwr.wanderway.presentation.entryScreens.register


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.OnPrimaryTextField
import com.pwr.wanderway.presentation.entryScreens.commons.EntryScreenLayout
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = RegisterViewModel(),
    onRegisterSucess: () -> Unit,
    onGoBackClick: () -> Unit
) {
    EntryScreenLayout(
        title = stringResource(id = R.string.register),
        content = {
            Column {
                OnPrimaryTextField(
                    value = "",
                    onValueChange = {},
                    label = stringResource(id = R.string.email)
                )
                Spacer(modifier = Modifier.size(16.dp))
                OnPrimaryTextField(
                    value = "",
                    onValueChange = {},
                    label = stringResource(id = R.string.login)
                )
                Spacer(modifier = Modifier.size(16.dp))
                OnPrimaryTextField(
                    value = "",
                    onValueChange = {},
                    label = stringResource(id = R.string.password)
                )
                Spacer(modifier = Modifier.size(16.dp))
                OnPrimaryTextField(
                    value = "",
                    onValueChange = {},
                    label = stringResource(id = R.string.password)
                )


            }
        },
        leftButton = stringResource(id = R.string.go_back),
        leftButtonOnClick = { onGoBackClick() },
        rightButtonOnClick = { onRegisterSucess() },
        rightButton = stringResource(id = R.string.register),
    )

}

@Preview
@Composable

fun RegisterScreenPreview() {
    AppTheme {
        RegisterScreen(RegisterViewModel(), {}, {})
    }
}
