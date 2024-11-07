package com.pwr.wanderway.presentation.routeCore.buildYourRoute

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.R
import com.pwr.wanderway.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildYourRouteScreen() {
    Text("essa")
    CenterAlignedTopAppBar(
        title = { Text(stringResource(id = R.string.build_your_route)) },
        actions = {},
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(horizontal = 16.dp),
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Forum"
            )
        })
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun BuildYourRouteScreenPreview() {
    AppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { NavigationBar {} }
        ) {
            BuildYourRouteScreen()
        }
    }
}