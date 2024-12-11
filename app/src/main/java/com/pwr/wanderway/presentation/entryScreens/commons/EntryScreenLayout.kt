package com.pwr.wanderway.presentation.entryScreens.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.R
import com.pwr.wanderway.presentation.commons.MainIcon
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun EntryScreenLayout(
    title: String,
    content: @Composable () -> Unit,
    rightButton: String? = null,
    rightButtonOnClick: (() -> Unit)? = null,
    leftButton: String? = null,
    leftButtonOnClick: (() -> Unit)? = null
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding() // Adjusts for system UI insets
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.welcome_background),
                contentDescription = null, // Decorative background image
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        alpha = 0.9f
                    },
                contentScale = ContentScale.Crop // // Adjusts how the image is scaled to fit the background
            )

            ElevatedCard(
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .width(width = 300.dp).imePadding().verticalScroll(rememberScrollState()),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        MainIcon()
                        Text(
                            text = title,
                            style = MaterialTheme.typography.headlineSmall
                        )
                        content()
                    }

                    Row(modifier = Modifier.align(Alignment.End)) {
                        leftButton?.let {
                            TextButton(
                                colors = ButtonDefaults.textButtonColors(
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                ),
                                onClick = {
                                    leftButtonOnClick?.invoke()
                                }
                            ) {
                                Text(leftButton)
                            }
                        }
                        rightButton?.let {
                            TextButton(
                                colors = ButtonDefaults.textButtonColors(
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                ),
                                onClick = {
                                    rightButtonOnClick?.invoke()
                                }
                            ) {
                                Text(rightButton)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun LayoutPreview() {
    AppTheme {
        EntryScreenLayout(
            title = "Title",
            content = {
                Text("Hello, World!")
            },
            rightButton = "Right",
            rightButtonOnClick = {},
            leftButton = "Left",
            leftButtonOnClick = {}
        )

    }
}
