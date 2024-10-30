package com.pwr.wanderway.presentation.navbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.navigation.DefaultNavigator
import com.pwr.wanderway.navigation.Destination

@Composable
fun AuthenticatedWrapper(
    viewModel: AuthenticatedWrapperViewModel,
    content: @Composable () -> Unit
) {
    val currentDestination by viewModel.currentDestination.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)
        ) {
            content()
        }
        NavBar(modifier = Modifier.align(Alignment.BottomCenter),
            currentDestination = currentDestination,
            onHomeClicked = { viewModel.onHomeClicked() },
            onAccountClicked = { viewModel.onAccountSettingsClicked() },
            onForumClicked = { viewModel.onForumClicked() }
        )
    }
}

@Preview
@Composable
fun AuthenticatedWrapperPreview() {
    AuthenticatedWrapper(
        viewModel = AuthenticatedWrapperViewModel(navigator =DefaultNavigator(Destination.ForumHomeScreen)),
        content = {
            Text("Content")
        }
    )
}





