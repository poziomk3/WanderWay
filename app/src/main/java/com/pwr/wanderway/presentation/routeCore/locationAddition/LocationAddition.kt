package com.pwr.wanderway.presentation.routeCore.locationAddition

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.routeCore.commons.RouteSurface
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun LocationAdditionScreen() {
    RouteSurface(
        title = stringResource(id = R.string.add_location_title),
        onGoBack = { /* TODO */ }
    )

    {
        LazyColumn {
            items(100) { index -> // Example list items
                Text("Item #$index", modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
@Preview
fun LocationAdditionScreenPreview() {
    AppTheme {
        LocationAdditionScreen()
    }
}