package com.pwr.wanderway.presentation.forum

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pwr.wanderway.data.model.api.forum.PublicPost
import com.pwr.wanderway.presentation.commons.Loader
import com.pwr.wanderway.presentation.forum.commons.ForumPostCard

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForumHomeScreen(
    forumPostNav: (Int) -> Unit,
    forumViewModel: ForumViewModel = hiltViewModel()
) {
    val isLoading by forumViewModel.isLoading.collectAsState()
    val posts by produceState<List<PublicPost>>(initialValue = emptyList()) {
        value = forumViewModel.getAllPosts()
    }

    if (isLoading) {
        Loader()
    } else
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(posts) { post ->
                    ForumPostCard(
                        post = post, onClick = { forumPostNav(post.id) }
                    )
                }
            }

        }
}
