package com.pwr.wanderway.navigation.authorized


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
import androidx.compose.ui.res.stringResource
import com.pwr.wanderway.R

@Composable
fun BottomBar(
    homeNav: () -> Unit,
    forumNav: () -> Unit,
    accountNav: () -> Unit
) {
    val navItems = listOf(
        Triple(forumNav, Icons.Filled.ThumbUp, R.string.bottom_bar_forum),
        Triple(homeNav, Icons.Filled.Home, R.string.bottom_bar_home),
        Triple(accountNav, Icons.Filled.AccountCircle, R.string.bottom_bar_account)
    )

    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        navItems.forEach { (onClick, iconRes, labelRes) ->
            NavigationBarItem(
                selected = false, // Update with actual selection logic if needed
                onClick = onClick,
                icon = {
                    Icon(
                        imageVector = iconRes,
                        contentDescription = stringResource(labelRes)
                    )
                },
                label = { Text(stringResource(labelRes)) }
            )
        }
    }
}
