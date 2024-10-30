package com.pwr.wanderway.presentation.navbar

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
import androidx.compose.ui.Modifier
import com.pwr.wanderway.navigation.Destination

@Composable
fun NavBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onHomeClicked: () -> Unit = {},
    onAccountClicked: () -> Unit = {},
    onForumClicked: () -> Unit = {}
) {
    NavigationBar(modifier = modifier.fillMaxWidth()) {
        NavigationBarItem(
            selected = currentDestination == Destination.ForumHomeScreen,
            onClick = { onForumClicked() },
            icon = { Icon(imageVector = Icons.Filled.ThumbUp, contentDescription = "Forum") },
            label = { Text("Forum") }
        )
        NavigationBarItem(
            selected = currentDestination == Destination.HomeScreen,
            onClick = { onHomeClicked() },
            icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = currentDestination == Destination.AccountSettingsHomeScreen,
            onClick = { onAccountClicked() },
            icon = { Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Settings") },
            label = { Text("Account") }
        )
    }
}
