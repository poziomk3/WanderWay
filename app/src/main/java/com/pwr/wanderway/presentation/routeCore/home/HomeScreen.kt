package com.pwr.wanderway.presentation.routeCore.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.presentation.routeCore.commons.MapComponent.MapComponent
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun HomeScreen(
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.home_screen_label_1),
                    style = MaterialTheme.typography.titleSmall.copy(fontStyle = FontStyle.Italic),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = stringResource(R.string.home_screen_label_2),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

        }
        MapComponent(
            myLocationButton = true,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        WideButton(ButtonColor.PRIMARY, stringResource(R.string.home_screen_button), onClick = onClick)
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(onClick = {})
    }
}


