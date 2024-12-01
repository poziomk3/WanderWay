package com.pwr.wanderway.presentation.routeCore.locationAddition

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.presentation.routeCore.commons.MapComponent.MapComponent
import com.pwr.wanderway.presentation.routeCore.commons.SearchBarWithSuggestions
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun LocationAdditionScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.add_location_screen_description),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        SearchBarWithSuggestions()
        MapComponent(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        WideButton(
            text = stringResource(id = R.string.add_location_screen_button),
            onClick = { /* TODO */ },
            colorType = ButtonColor.PRIMARY
        )
    }
}


@Composable
@Preview
fun LocationAdditionScreenPreview() {
    AppTheme {
        Surface {
            LocationAdditionScreen()
        }
    }
}