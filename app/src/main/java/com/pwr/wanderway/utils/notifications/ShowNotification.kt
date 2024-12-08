package com.pwr.wanderway.utils.notifications

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.pwr.wanderway.R

fun showNotification(context: Context, title: String, message: String) {
    val channelId = "default_channel_id"
    val notificationId = 1

    // Check for POST_NOTIFICATIONS permission (Android 13+)
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return // Permission not granted; exit function
        }
    }

    val notification = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.icon) // Replace with your app icon
        .setContentTitle(title)
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true)
        .build()

    NotificationManagerCompat.from(context).notify(notificationId, notification)
}
