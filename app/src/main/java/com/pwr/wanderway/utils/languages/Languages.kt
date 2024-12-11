package com.pwr.wanderway.utils.languages

import android.content.Context
import java.util.Locale

enum class Languages(val localeCode: String, val locale: Locale, val flagEmoji: String) {
    ENGLISH("en", Locale("en"), "\uD83C\uDDEC\uD83C\uDDE7"),  // 🇬🇧 for English (UK)
    POLISH("pl", Locale("pl"), "\uD83C\uDDF5\uD83C\uDDF1"),   // 🇵🇱 for Poland
    GERMAN("de", Locale("de"), "\uD83C\uDDE9\uD83C\uDDEA"),   // 🇩🇪 for Germany
    UKRAINIAN("uk", Locale("uk"), "\uD83C\uDDFA\uD83C\uDDE6"), // 🇺🇦 for Ukraine
    SPANISH("es", Locale("es"), "\uD83C\uDDEA\uD83C\uDDF8");   // 🇪🇸 for Spain

    companion object {
        fun getFlagByLocaleCode(code: String): String {
            return entries.find { it.localeCode == code.lowercase() }?.flagEmoji ?: ""
        }
    }
}

fun getCurrentLocale(context: Context): Locale {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        context.resources.configuration.locales[0] // For Android N and above
    } else {
        @Suppress("DEPRECATION")
        context.resources.configuration.locale // For older versions
    }
}