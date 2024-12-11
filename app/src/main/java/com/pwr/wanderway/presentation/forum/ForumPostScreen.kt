package com.pwr.wanderway.presentation.forum

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.pwr.wanderway.R
import com.pwr.wanderway.data.model.api.forum.PublicPost
import com.pwr.wanderway.network.ImgUrl
import com.pwr.wanderway.network.RouteImageType
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.Loader
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.presentation.forum.commons.StarRatingBar
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForumPostScreen(
    postId: Int, routeDisplayNav: (Int) -> Unit, forumViewModel: ForumViewModel = hiltViewModel()

) {

    val isLoading by forumViewModel.isLoading.collectAsState()
    val post by produceState<PublicPost?>(initialValue = null) {
        value = forumViewModel.getPost(postId)
    }
    if (isLoading) {
        Loader()
    } else if (post != null) {
        val (title, body, rating, id, routeId, author, date, img_url) = post!!

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // Author and Date
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$author",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = date.toInstant().atZone(ZoneId.systemDefault())
                        .format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Card(
                shape = RoundedCornerShape(12.dp), modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImgUrl.getRouteImg(
                            routeId, RouteImageType.ROADMAP
                        )
                    ),
                    contentDescription = "Route Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Description
            Text(
                text = body,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            // Rating Section
            StarRatingBar(rating = rating, onRatingChanged = {})

            // Action Button
            WideButton(
                text = stringResource(R.string.forum_post_screen_route_button),
                onClick = { routeDisplayNav(routeId) },
                colorType = ButtonColor.PRIMARY
            )
        }
    }
}