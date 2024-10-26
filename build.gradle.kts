import java.util.Properties

val properties = Properties().apply {
    file("env.properties").inputStream().use { load(it) }
}

allprojects {
    ext["BACKEND_URL"] = properties["BACKEND_URL"]
}

plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}