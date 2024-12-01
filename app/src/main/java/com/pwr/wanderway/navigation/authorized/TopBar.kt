package com.pwr.wanderway.navigation.authorized

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pwr.wanderway.R
import com.pwr.wanderway.navigation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    route: Destination?,
    onNavigationIconClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
        title = { Text(stringResource(id = getTopBarTitle(route))) },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back-navigation"
                )
            }
        }
    )
}


@Composable
fun getTopBarTitle(route: Destination?): Int {
    return when (route) {
        Destination.HOME_SCREEN -> R.string.top_bar_home
        Destination.BUILD_YOUR_OWN_ROUTE_SCREEN -> R.string.top_bar_build_your_route
        Destination.FORUM_SCREEN -> R.string.top_bar_forum
        Destination.ACCOUNT_SETTINGS_SCREEN -> R.string.top_bar_account_settings
        Destination.PREFERENCES_SCREEN -> R.string.top_bar_preferences
        Destination.ROUTE_CHOICE_SCREEN -> R.string.top_bar_route_choice
        Destination.ROUTE_DISPLAY_SCREEN -> R.string.top_bar_route_display
        else -> R.string.top_bar_default
    }
}


@Preview
@Composable
fun TopBarPreview() {
    TopBar(route = Destination.HOME_SCREEN)
}