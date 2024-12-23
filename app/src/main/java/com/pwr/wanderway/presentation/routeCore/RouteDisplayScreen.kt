package com.pwr.wanderway.presentation.routeCore

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.pwr.wanderway.R
import com.pwr.wanderway.network.ImgUrl
import com.pwr.wanderway.network.RouteImageType
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.Loader
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.ui.theme.AppTheme
import com.pwr.wanderway.utils.notifications.showNotification
import kotlinx.coroutines.launch

@Composable
fun RouteDisplayScreen(
    buildYourOwnRouteNav: () -> Unit,
    forumAdditionNav: () -> Unit,
    routeId: Int,
    routeViewModel: RouteViewModel,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val loading by routeViewModel.loading.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    if (loading) {
        Loader()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImgUrl.getRouteImg(
                        routeId,
                        RouteImageType.HYBRID
                    )
                ),
                contentDescription = "Route Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 30.dp)
            ) {
                WideButton(
                    text = stringResource(id = R.string.route_display_screen_restart),
                    onClick = {
                        routeViewModel.emptyPointsOfInterest()
                        buildYourOwnRouteNav()
                    },
                    colorType = ButtonColor.SECONDARY
                )
                WideButton(
                    text = stringResource(id = R.string.route_display_screen_modify_route),
                    onClick = { buildYourOwnRouteNav() },
                    colorType = ButtonColor.SECONDARY
                )
            }

            WideButton(
                text = stringResource(id = R.string.route_display_screen_go_for_it),
                onClick = {
                    coroutineScope.launch {
                        routeViewModel.redirectToGoogleMaps(routeId, context)
                        showDialog = true
                        showNotification(
                            context,
                            context.getString(R.string.route_display_screen_notification_title),
                            context.getString(R.string.route_display_screen_notification_message)
                        )
                        Toast.makeText(
                            context,
                            context.getString(R.string.route_display_screen_toast_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                },
                colorType = ButtonColor.PRIMARY
            )
        }
        if (showDialog) {
            AlertDialog(
                icon = null,
                title = {
                    Text(stringResource(R.string.bottom_bar_forum))
                },
                text = {
                    Text(stringResource(R.string.route_display_screen_rate_this_route))
                },
                onDismissRequest = {},
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        forumAdditionNav()
                    }) {
                        Text(stringResource(R.string.route_display_screen_go_to_forum))
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDialog = false
                    }) {
                        Text(stringResource(R.string.route_display_screen_dismiss_dialog))
                    }
                })
        }


    }
}

@Preview
@Composable
fun RouteDisplayScreenPreview() {
    AppTheme {
        Surface {
            RouteDisplayScreen(
                routeId = 0,
                buildYourOwnRouteNav = {},
                forumAdditionNav = {},
                routeViewModel = hiltViewModel()
            )
        }
    }
}

