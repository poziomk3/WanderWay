package com.pwr.wanderway.navigation.authorized

import androidx.compose.foundation.layout.RowScope
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
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.utils.mappers.topBarDisplayNames

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    route: Destination?,
    onNavigationIconClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
        title = { Text(topBarDisplayNames[route]?.let { stringResource(id = it) } ?: "") },
        navigationIcon = {
            if (onNavigationIconClick != null) { // Show icon only if onNavigationIconClick is not null
                IconButton(onClick = onNavigationIconClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back-navigation"
                    )
                }
            }
        },
        actions = actions
    )
}


@Preview
@Composable
fun TopBarPreview() {
    TopBar(route = Destination.HOME_SCREEN)
}