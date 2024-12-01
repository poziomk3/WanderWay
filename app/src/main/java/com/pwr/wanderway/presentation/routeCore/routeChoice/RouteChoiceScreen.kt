package com.pwr.wanderway.presentation.routeCore.routeChoice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.routeCore.commons.RouteCard
import com.pwr.wanderway.presentation.routeCore.commons.RouteSurface
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun RouteChoiceScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        RouteSurface(
            title = stringResource(id = R.string.route_choice_screen_title),
            onGoBack = { /* TODO */ }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(List(5) { 0 }) { index, _ ->
                    RouteCard(
                        index + 1
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .background(MaterialTheme.colorScheme.errorContainer)
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun PreferencesScreenScreenPreview() {
    AppTheme {
        Surface {
            RouteChoiceScreen()
        }
    }
}