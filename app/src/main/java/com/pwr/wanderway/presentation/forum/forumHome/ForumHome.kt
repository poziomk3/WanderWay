package com.pwr.wanderway.presentation.forum.forumHome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.presentation.forum.commons.ForumPostCard

@Composable
fun ForumHome() {
    Surface(
        color = MaterialTheme.colorScheme.errorContainer,
        modifier = Modifier.fillMaxSize()

    )
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(List(5) { 0 }) { index, _ ->
                ForumPostCard(
                    user = "John Doe",
                    routeName = "Trail to Blue Lake",
                    title = "Spectacular Views at Blue Lake",
                    imgUrl = "https://picsum.photos/200/300", // Placeholder image
                    onClick = { /* Handle click */ }
                )
            }
        }
    }
}
