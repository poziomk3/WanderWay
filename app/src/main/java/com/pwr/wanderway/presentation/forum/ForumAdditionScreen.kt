package com.pwr.wanderway.presentation.forum

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.ButtonColor
import com.pwr.wanderway.presentation.commons.Loader
import com.pwr.wanderway.presentation.commons.WideButton
import com.pwr.wanderway.presentation.forum.commons.StarRatingBar


@Composable
fun ForumAdditionScreen(
    routeId: Int,
    forumHomeNav: () -> Unit,
    forumViewModel: ForumViewModel = hiltViewModel()
) {

    val isLoading by forumViewModel.isLoading.collectAsState()

    var title by remember { mutableStateOf("") }
    var post by remember { mutableStateOf("") }
    var rating by remember { mutableIntStateOf(0) }


    val routeIdFocusRequester = remember { FocusRequester() }
    val titleFocusRequester = remember { FocusRequester() }
    val postFocusRequester = remember { FocusRequester() }

    val isFormValid = title.isNotBlank() && post.isNotBlank() && rating > 0

    if (isLoading)
        Loader()
    else
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .imePadding(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = routeId.toString(),
                enabled = false,
                onValueChange = { },
                label = { Text(stringResource(R.string.forum_addition_screen_route_id)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(onNext = { titleFocusRequester.requestFocus() }),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(routeIdFocusRequester)
            )
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(stringResource(R.string.forum_addition_screen_post_title)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(onNext = { postFocusRequester.requestFocus() }),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(titleFocusRequester)
            )
            TextField(
                value = post,
                onValueChange = { post = it },
                label = { Text(stringResource(R.string.forum_addition_screen_post_content)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done, keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions.Default,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(postFocusRequester)
                    .height(150.dp),
                maxLines = 5
            )
            Text(
                text = stringResource(R.string.forum_addition_screen_rating),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start)
            )
            StarRatingBar(maxStars = 5, rating = rating, onRatingChanged = { rating = it })

            WideButton(
                onClick = {
                    forumViewModel.addPost(routeId, title, post, rating)
                    forumHomeNav()
                }, enabled = isFormValid, text = "Submit post", colorType = ButtonColor.PRIMARY
            )
        }
}

@Preview
@Composable
fun ForumAdditionScreenPreview() {
    ForumAdditionScreen(0, forumHomeNav = {})
}

