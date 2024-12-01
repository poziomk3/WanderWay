package com.pwr.wanderway.navigation


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pwr.wanderway.R

@Composable
fun NavBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        NavigationBarItem(
            selected = currentDestination == Destination.FORUM_SCREEN,
            onClick = { navController.navigate(Destination.FORUM_SCREEN) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.ThumbUp,
                    contentDescription = stringResource(R.string.bottom_bar_forum)
                )
            },
            label = { Text(stringResource(R.string.bottom_bar_forum)) }
        )
        NavigationBarItem(
            selected = currentDestination == Destination.HOME_SCREEN,
            onClick = { navController.navigate(Destination.HOME_SCREEN) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = stringResource(R.string.bottom_bar_home)
                )
            },
            label = { Text(stringResource(R.string.bottom_bar_home)) }
        )
        NavigationBarItem(
            selected = currentDestination == Destination.ACCOUNT_SETTINGS_SCREEN,
            onClick = { navController.navigate(Destination.ACCOUNT_SETTINGS_SCREEN) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = stringResource(R.string.bottom_bar_account)
                )
            },
            label = { Text(stringResource(R.string.bottom_bar_account)) }
        )
    }
}