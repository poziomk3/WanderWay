package com.pwr.wanderway.presentation.forum

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.presentation.forum.commons.ForumPostCard

@Composable
fun ForumHomeScreen(
    forumAdditionNav: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // LazyColumn displaying forum posts
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(List(5) { 0 }) { index, _ ->
                ForumPostCard(
                    user = "John Doe",
                    routeName = "Trail to Blue Lake",
                    title = "Spectacular Views at Blue Lake",
                    imgUrl = "https://picsum.photos/200/300",
                    onClick = { /* Handle click */ }
                )
            }
        }

        // Floating Action Button
        FloatingActionButton(
            onClick = { forumAdditionNav() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp) // Padding from screen edges
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Post"
            )
        }
    }
}
