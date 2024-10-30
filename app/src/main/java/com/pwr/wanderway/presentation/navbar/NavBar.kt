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
import androidx.compose.ui.tooling.preview.Preview
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun NavBar(
    modifier: Modifier = Modifier,
    onHomeClicked: () -> Unit = {},
    onSearchClicked: () -> Unit = {},
    onForumClicked: () -> Unit = {}
) {
    NavigationBar(modifier = modifier.fillMaxWidth()) {
        NavigationBarItem(
            selected = false,
            onClick = { onHomeClicked() },
            icon = { Icon(imageVector = Icons.Filled.ThumbUp, contentDescription = "Forum") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { onSearchClicked() },
            icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Search") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { onForumClicked() },
            icon = { Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Settings") },
            label = { Text("Forum") }
        )
    }
}
@Preview
@Composable
fun NavBarPreview() {
    AppTheme {
        NavBar()
    }
}