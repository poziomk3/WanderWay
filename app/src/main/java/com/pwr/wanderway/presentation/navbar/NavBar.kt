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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pwr.wanderway.navigation.Destination

@Composable
fun NavBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        NavigationBarItem(
            selected = currentDestination == Destination.Forum,
            onClick = { navController.navigate(Destination.Forum) },
            icon = { Icon(imageVector = Icons.Filled.ThumbUp, contentDescription = "Forum") },
            label = { Text("Forum") }
        )
        NavigationBarItem(
            selected = currentDestination == Destination.HomeScreen,
            onClick = { navController.navigate(Destination.HomeScreen) },
            icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = currentDestination == Destination.AccountSettings,
            onClick = { navController.navigate(Destination.AccountSettings) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Settings"
                )
            },
            label = { Text("Account") }
        )
    }
}
