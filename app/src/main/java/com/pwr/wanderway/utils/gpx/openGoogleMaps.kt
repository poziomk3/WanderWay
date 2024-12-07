package com.pwr.wanderway.utils.gpx

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun openGoogleMapsWithWaypoints(context: Context, waypoints: List<Pair<Double, Double>>) {
    val origin = waypoints.first()
    val destination = waypoints.last()
    val waypointsQuery = waypoints.subList(1, waypoints.size - 1)
        .joinToString("|") { "${it.first},${it.second}" }

    val uri = Uri.parse(
        "https://www.google.com/maps/dir/?api=1" +
                "&origin=${origin.first},${origin.second}" +
                "&destination=${destination.first},${destination.second}" +
                "&waypoints=$waypointsQuery"
    )
    val intent = Intent(Intent.ACTION_VIEW, uri)
    intent.setPackage("com.google.android.apps.maps")

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "Google Maps is not installed", Toast.LENGTH_SHORT).show()
    }
}