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
import androidx.compose.ui.graphics.Color
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
            activeDestination == Destination.FORUM_HOME_SCREEN
        ),
        Quadruple(
            homeNav,
            Icons.Filled.Home,
            R.string.bottom_bar_home,
            activeDestination !in listOf(
                Destination.FORUM_HOME_SCREEN,
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
        containerColor = Color(0xFF36512F),
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
                    indicatorColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = Color.White
                ),
                label = {
                    Text(
                        stringResource(labelRes)
                    )
                }
            )
        }
    }
}
