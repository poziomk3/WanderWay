package com.pwr.wanderway.presentation.forum.commons

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ForumPostCard(
    user: String,
    routeName: String,
    title: String,
    imgUrl: String, // This could be an avatar or route image
    onClick: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image at the top

            Image(
                painter = rememberAsyncImagePainter(imgUrl),
                contentDescription = "Route Image",
                contentScale = ContentScale.Crop, // Ensures the image fills and crops appropriately
                modifier = Modifier
                    .height(200.dp) // Set the desired height
                    .fillMaxWidth() // Make it span the full width
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
                        .fillMaxWidth().padding(4.dp,0.dp),

                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = user,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = routeName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    text = title,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 4.dp),
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewForumPostCard() {
    ForumPostCard(
        user = "John Doe",
        routeName = "Trail to Blue Lake",
        title = "Spectacular Views at Blue Lake",
        imgUrl = "https://media.geeksforgeeks.org/wp-content/uploads/20210101144014/gfglogo.png", // Placeholder image
        onClick = { /* Handle click */ }
    )
}
