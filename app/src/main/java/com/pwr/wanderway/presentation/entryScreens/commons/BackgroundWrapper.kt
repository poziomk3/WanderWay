package com.pwr.wanderway.presentation.entryScreens.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.pwr.wanderway.R
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun WelcomeBackgroundWrapper(
    modifier: Modifier = Modifier, // Allow custom modifier if needed
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary // Background color
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background Image
            Image(
                painter = painterResource(id = R.drawable.welcome_background),
                contentDescription = null, // Decorative background image
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // Scale image to fill the surface
            )
            // Centered content
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content() // Display provided content
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BackgroundWrapperPreview() {
    AppTheme {
        WelcomeBackgroundWrapper {
            Text(
                text = "THIS is test!",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}