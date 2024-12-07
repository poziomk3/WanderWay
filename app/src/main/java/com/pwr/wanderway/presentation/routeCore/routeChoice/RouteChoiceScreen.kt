package com.pwr.wanderway.presentation.routeCore.routeChoice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.pwr.wanderway.network.ImgUrl
import com.pwr.wanderway.network.RouteImageType
import com.pwr.wanderway.presentation.commons.Loader
import com.pwr.wanderway.presentation.routeCore.RouteViewModel
import com.pwr.wanderway.presentation.routeCore.commons.RouteCard
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun RouteChoiceScreen(
    routeDisplayNav: (routeNumber: Int) -> Unit,
    routeViewModel: RouteViewModel,
) {
    val routeIds by produceState<List<Int>>(initialValue = emptyList()) {
        value = routeViewModel.generateRoutes() ?: emptyList()
    }
    val loading by routeViewModel.loading.collectAsState()
    if (loading) {
        Loader()
    } else
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(routeIds) { routeId ->
                RouteCard(
                    number = routeId,
                    onClick = {
                        routeDisplayNav(routeId)
                    },
                ) {

                    Image(
                        painter = rememberAsyncImagePainter(
                            ImgUrl.getRouteImg(
                                routeId,
                                RouteImageType.HYBRID
                            )
                        ),
                        contentDescription = "Route Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    )


                }
            }
        }
}


@Composable
@Preview
fun PreferencesScreenScreenPreview() {
    AppTheme {
        Surface {
            RouteChoiceScreen(
                routeDisplayNav = {},
                routeViewModel = hiltViewModel(),
            )
        }
    }
}