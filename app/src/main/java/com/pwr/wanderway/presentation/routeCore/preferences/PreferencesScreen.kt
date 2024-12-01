package com.pwr.wanderway.presentation.routeCore.preferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.presentation.routeCore.commons.Dropdown
import com.pwr.wanderway.ui.theme.AppTheme


@Composable
fun PreferencesScreen(viewModel: PreferencesViewModel = viewModel()) {
    val dropdownConfigs = viewModel.dropdownConfigs

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 8.dp, bottom = 6.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp) // Allow the column to take only the available space
        ) {
            items(dropdownConfigs) { config ->
                Dropdown(config = config)
            }
        }

        WideButton(
            text = stringResource(id = R.string.go_to_advanced),
            onClick = { /* TODO */ },
            colorType = ButtonColor.SECONDARY
        )
        WideButton(
            text = stringResource(id = R.string.save),
            onClick = { /* TODO */ },
            colorType = ButtonColor.PRIMARY
        )
    }
}


@Composable
@Preview
fun PreferencesScreenScreenPreview() {
    AppTheme {
        Surface {
            PreferencesScreen()
        }
    }
}