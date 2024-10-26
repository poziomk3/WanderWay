package com.pwr.wanderway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.NavigationAction
import com.pwr.wanderway.navigation.Navigator
import com.pwr.wanderway.navigation.ObserveAsEvents
import com.pwr.wanderway.presentation.entryScreens.activateAccount.ActivateAccountScreen
import com.pwr.wanderway.presentation.entryScreens.activateAccount.ActivateAccountViewModel
import com.pwr.wanderway.presentation.entryScreens.login.LoginScreen
import com.pwr.wanderway.presentation.entryScreens.login.LoginViewModel
import com.pwr.wanderway.presentation.entryScreens.register.RegisterScreen
import com.pwr.wanderway.presentation.entryScreens.register.RegisterViewModel
import com.pwr.wanderway.presentation.entryScreens.welcome.WelcomeScreen
import com.pwr.wanderway.presentation.entryScreens.welcome.WelcomeViewModel
import com.pwr.wanderway.ui.theme.AppTheme
import org.koin.android.ext.android.get
import org.koin.androidx.compose.koinViewModel

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
                    composable<Destination.WelcomeScreen> {
                        val viewModel = koinViewModel<WelcomeViewModel>()
                        WelcomeScreen(viewModel)
                    }
                    composable<Destination.LoginScreen> {
                        val viewModel = koinViewModel<LoginViewModel>()
                        LoginScreen(
                            viewModel = viewModel,
                        )
                    }
                    composable<Destination.RegisterScreen> {
                        val viewModel = koinViewModel<RegisterViewModel>()
                        RegisterScreen(viewModel)
                    }
                    composable<Destination.ActivateAccountScreen> {
                        val viewModel = koinViewModel<ActivateAccountViewModel>()
                        ActivateAccountScreen(viewModel)
                    }
                }

            }
        }
    }
}



