package com.pwr.wanderway.presentation.forum

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ForumAdditionScreen(
    forumHomeNav: () -> Unit
) {
    Column {
        Text(
            "Forum addition"
        )
        Button (
                onClick = {
                    forumHomeNav()
                /* Handle button click */ }
                ) {
            Text("Add post")
        }


    }
}