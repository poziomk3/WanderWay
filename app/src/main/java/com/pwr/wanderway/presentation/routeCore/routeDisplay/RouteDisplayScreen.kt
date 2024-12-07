package com.pwr.wanderway.presentation.routeCore.routeDisplay

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import com.pwr.wanderway.presentation.routeCore.RouteViewModel
import com.pwr.wanderway.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun RouteDisplayScreen(
    buildYourOwnRouteNav: () -> Unit,
    routeId: String,
    routeDisplayViewModel: RouteDisplayViewModel = hiltViewModel(),
    routeViewModel: RouteViewModel,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val loading by routeDisplayViewModel.loading.collectAsState()

    if (loading) {
        Loader()
    } else
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImgUrl.getRouteImg(
                        routeId.toInt(),
                        RouteImageType.HYBRID
                    )
                ),
                contentDescription = "Route Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            WideButton(
                text = stringResource(id = R.string.route_display_screen_restart),
                onClick = {
                    routeViewModel.emptyPointsOfInterest()
                    buildYourOwnRouteNav()
                },
                colorType = ButtonColor.ERROR
            )
            WideButton(
                text = stringResource(id = R.string.route_display_modify_route),
                onClick = { buildYourOwnRouteNav() },
                colorType = ButtonColor.SECONDARY
            )
            WideButton(
                text = stringResource(id = R.string.route_display_go_for_it),
                onClick = {
                    coroutineScope.launch {
                        routeDisplayViewModel.handleRoute(routeId.toInt(), context)
                    }
                },
                colorType = ButtonColor.PRIMARY
            )
        }
}

@Preview
@Composable
fun RouteDisplayScreenPreview() {
    AppTheme {
        Surface {
            RouteDisplayScreen(
                routeId = "1",
                routeDisplayViewModel = hiltViewModel(),
                buildYourOwnRouteNav = {},
                routeViewModel = hiltViewModel()
            )
        }
    }
}

