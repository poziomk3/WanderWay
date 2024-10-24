package com.pwr.wanderway.presentation.welcomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.R
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun WelcomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(0.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Display the image in the background
            Image(
                painter = painterResource(id = R.drawable.welcome_background),
                contentDescription = null, // Decorative background image, so null is fine
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // Scale the image to fill the surface
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AlertDialog(
                    containerColor = MaterialTheme.colorScheme.primary,
                    textContentColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    icon = {
                        Image(
                            painter = painterResource(id = R.drawable.icon),
                            contentDescription = null,
                            modifier = Modifier.size(100.dp)
                        )
                    },
                    onDismissRequest = { /*TODO*/ },
                    title = {
                        Text(
                            text = stringResource(id = R.string.welcome_lable),
                            style = MaterialTheme.typography.headlineSmall
                        )
                    },
                    text = {
                        // Center the sublabel text
                        Box(
                            modifier = Modifier.fillMaxWidth(), // Ensures Box takes the full width of the dialog
                            contentAlignment = Alignment.Center // Centers the content inside the Box
                        ) {
                            Text(stringResource(id = R.string.welcome_sublabel))
                        }
                    },
                    confirmButton = {
                        TextButton(
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            onClick = {
                                // TODO: Handle login action
                            }
                        ) {
                            Text(stringResource(id = R.string.login))
                        }
                    },
                    dismissButton = {
                        TextButton(
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            onClick = {
                                // TODO: Handle register action
                            }
                        ) {
                            Text(stringResource(id = R.string.register))
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    AppTheme {
        WelcomeScreen()
    }
}
