package com.pwr.wanderway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pwr.wanderway.navigation.NavigationAction
import com.pwr.wanderway.navigation.Navigator
import com.pwr.wanderway.navigation.ObserveAsEvents
import com.pwr.wanderway.navigation.authorizedNavGraph
import com.pwr.wanderway.navigation.unauthorizedNavGraph
import com.pwr.wanderway.ui.theme.AppTheme
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()
                val navigator = get<Navigator>()
                ObserveAsEvents(flow = navigator.navigationActions) { action ->
                    when (action) {
                        is NavigationAction.Navigate -> navController.navigate(
                            action.destination
                        ) {
                            action.navOptions(this)
                        }

                        NavigationAction.NavigateUp -> navController.navigateUp()
                    }
                }
                NavHost(
                    navController = navController,
                    startDestination = navigator.startDestination,
                ) {
                    unauthorizedNavGraph()
                    authorizedNavGraph()
                }

            }
        }
    }
}

