package com.pwr.wanderway.presentation.forum.commons

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.pwr.wanderway.data.model.api.forum.PublicPost
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForumPostCard(
    post: PublicPost, onClick: () -> Unit
) {

    val (title, body, rating, id, routeId, author, date, img_url) = post
    ElevatedCard(elevation = CardDefaults.cardElevation(
        defaultElevation = 20.dp
    ), modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }) {
        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    img_url
                ),
                contentDescription = "Route Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp, 0.dp),

                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = author, maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = date.toInstant().atZone(ZoneId.systemDefault())
                            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                StarRatingBar(rating = rating, onRatingChanged = {})

            }
        }
    }
}


