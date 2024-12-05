package com.pwr.wanderway.navigation.authorized


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pwr.wanderway.R
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.utils.Quadruple

@Composable
fun BottomBar(
    activeDestination: Destination,
    homeNav: () -> Unit,
    forumNav: () -> Unit,
    accountNav: () -> Unit
) {

    val navItems = listOf(
        Quadruple(
            forumNav,
            Icons.Filled.ThumbUp,
            R.string.bottom_bar_forum,
            activeDestination == Destination.FORUM_SCREEN
        ),
        Quadruple(
            homeNav,
            Icons.Filled.Home,
            R.string.bottom_bar_home,
            activeDestination !in listOf(
                Destination.FORUM_SCREEN,
                Destination.ACCOUNT_SETTINGS_SCREEN
            )
        ),
        Quadruple(
            accountNav,
            Icons.Filled.AccountCircle,
            R.string.bottom_bar_account,
            activeDestination == Destination.ACCOUNT_SETTINGS_SCREEN
        )
    )
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        contentColor = MaterialTheme.colorScheme.onPrimary,
        containerColor = MaterialTheme.colorScheme.secondary,
    ) {
        navItems.forEach { (onClick, iconRes, labelRes, activeDestination) ->
            NavigationBarItem(

                selected = activeDestination, // Update with actual selection logic if needed
                onClick = onClick,
                icon = {
                    Icon(
                        imageVector = iconRes,

                        contentDescription = stringResource(labelRes)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.outlineVariant,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    selectedIconColor = MaterialTheme.colorScheme.scrim
                ),
                label = {
                    Text(
                        stringResource(labelRes),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
        }
    }
}
