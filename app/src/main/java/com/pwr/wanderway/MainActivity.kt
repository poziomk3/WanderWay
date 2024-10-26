package com.pwr.wanderway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.pwr.wanderway.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanderWay()
        }
    }
}


@Composable
fun WanderWay() {
    AppTheme {
        Navigation()
    }
}
