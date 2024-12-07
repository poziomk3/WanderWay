package com.pwr.wanderway.presentation.routeCore.routeDisplay

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.pwr.wanderway.data.repository.RouteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import javax.inject.Inject


@HiltViewModel
class RouteDisplayViewModel @Inject constructor(
    private val routeRepository: RouteRepository,
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    fun parseGpxFile(inputStream: InputStream): List<Pair<Double, Double>> {
        val waypoints = mutableListOf<Pair<Double, Double>>()

        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()
        parser.setInput(inputStream, null)

        var eventType = parser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && parser.name == "wpt") {
                val lat = parser.getAttributeValue(null, "lat")?.toDoubleOrNull()
                val lon = parser.getAttributeValue(null, "lon")?.toDoubleOrNull()
                if (lat != null && lon != null) {
                    waypoints.add(Pair(lat, lon))
                }
            }
            eventType = parser.next()
        }

        return waypoints
    }


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


    suspend fun handleRoute(routeId: Int, context: Context) {
        _loading.value = true
        try {
            val inputStream = routeRepository.getRouteById(routeId)

            val waypoints = parseGpxFile(inputStream)
            openGoogleMapsWithWaypoints(context, waypoints)
        } catch (e: Exception) {
            Toast.makeText(context, "Failed to process route: ${e.message}", Toast.LENGTH_LONG)
                .show()
        } finally {
            _loading.value = false
        }
    }

}



