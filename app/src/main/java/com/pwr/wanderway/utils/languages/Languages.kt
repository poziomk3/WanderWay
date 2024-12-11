package com.pwr.wanderway.utils.languages

import java.util.Locale

enum class Languages(val code: String, val locale: Locale, val flagEmoji: String) {
    ENGLISH("en", Locale("en"), "\uD83C\uDDEC\uD83C\uDDE7"),  // 🇬🇧 for English (UK)
    POLISH("pl", Locale("pl"), "\uD83C\uDDF5\uD83C\uDDF1"),   // 🇵🇱 for Poland
    GERMAN("de", Locale("de"), "\uD83C\uDDE9\uD83C\uDDEA"),   // 🇩🇪 for Germany
    UKRAINIAN("uk", Locale("uk"), "\uD83C\uDDFA\uD83C\uDDE6"), // 🇺🇦 for Ukraine
    SPANISH("es", Locale("es"), "\uD83C\uDDEA\uD83C\uDDF8");   // 🇪🇸 for Spain

    companion object {
        fun getFlagByLocaleCode(code: String): String {
            return entries.find { it.code == code.lowercase() }?.flagEmoji ?: ""
        }
    }
}
