package com.pwr.wanderway.presentation.accountSettings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.R
import com.pwr.wanderway.data.model.RowSelectorConfig
import com.pwr.wanderway.presentation.commons.MainIcon
import com.pwr.wanderway.presentation.commons.RowSelector

@Composable
fun SettingsScreen(preferencesNav: () -> Unit) {

    val options = listOf(
        RowSelectorConfig(label = stringResource(R.string.settings_preferences), onClick = preferencesNav),
        RowSelectorConfig(label = stringResource(R.string.settings_language), onClick = {}),
        RowSelectorConfig(label = stringResource(R.string.settings_notifications), onClick = {}),
        RowSelectorConfig(label = stringResource(R.string.settings_privacy), onClick = {}),
        RowSelectorConfig(label = stringResource(R.string.settings_security), onClick = {}),
        RowSelectorConfig(label = stringResource(R.string.settings_help), onClick = {}),
        RowSelectorConfig(label = stringResource(R.string.settings_delete_account), onClick = {}),
        RowSelectorConfig(label = stringResource(R.string.settings_logout), onClick = {})
    )



    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            HorizontalDivider(thickness = 2.dp)
        }
        items(options) { item ->
            RowSelector(
                config = item
            )
            HorizontalDivider(thickness = 2.dp)
        }

        item { MainIcon(100) }
    }
}

@Composable
@Preview
fun SettingsScreenPreview() {
    SettingsScreen(preferencesNav = {})
}