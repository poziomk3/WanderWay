package com.pwr.wanderway.utils.gpx

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream

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