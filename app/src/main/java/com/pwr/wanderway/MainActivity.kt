package com.pwr.wanderway

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.compose.rememberNavController
import com.pwr.wanderway.navigation.RootNavigationGraph
import com.pwr.wanderway.ui.theme.AppTheme
import com.pwr.wanderway.utils.notifications.createNotificationChannel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val grantedPermissions = permissions.filterValues { it }
            val deniedPermissions = permissions.filterValues { !it }
            if (grantedPermissions.isNotEmpty()) {
                Toast.makeText(
                    this,
                    "Permissions granted: ${grantedPermissions.keys.joinToString()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (deniedPermissions.isNotEmpty()) {
                Toast.makeText(
                    this,
                    "Permissions denied: ${deniedPermissions.keys.joinToString()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotificationChannel(this)
        requestPermissionsOnLaunch()
        enableEdgeToEdge()
        setContent {
            AppTheme {
                RootNavigationGraph(navController = rememberNavController())
            }
        }
    }

    private fun requestPermissionsOnLaunch() {
        val permissionsToRequest = mutableListOf<String>()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionsToRequest.add(Manifest.permission.POST_NOTIFICATIONS)
        }

        permissionsToRequest.addAll(
            listOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
        requestPermissionsLauncher.launch(permissionsToRequest.toTypedArray())
    }
}
