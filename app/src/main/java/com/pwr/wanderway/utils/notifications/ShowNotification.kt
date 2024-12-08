package com.pwr.wanderway.utils.notifications

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.pwr.wanderway.MainActivity
import com.pwr.wanderway.R

fun showNotification(context: Context, title: String, message: String) {
    val channelId = "default_channel_id"
    val notificationId = 1

    val intent = Intent(context, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val notification = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.icon) // Replace with your app icon
        .setContentTitle(title)
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(pendingIntent) // Attach the PendingIntent
        .setAutoCancel(true) // Auto-dismiss when clicked
        .build()

    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // Permission not granted, exit the function
        return
    }

    // Show the notification
    NotificationManagerCompat.from(context).notify(notificationId, notification)
}
