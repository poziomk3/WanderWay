import java.util.Properties

val envProperties = Properties().apply {
    val envFile = rootProject.file("env.properties")
    if (envFile.exists()) {
        load(envFile.inputStream())
    }
}


val debugBackendUrl = envProperties["DEBUG_BACKEND_URL"]
val debugGoogleApiKey = envProperties["DEBUG_GOOGLE_API_KEY"]

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.pwr.wanderway"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pwr.wanderway"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField("String", "GOOGLE_API_KEY", "\"$debugGoogleApiKey\"")
            buildConfigField("String", "BACKEND_URL", "\"$debugBackendUrl\"")
        }
        release {
            buildConfigField("String", "GOOGLE_API_KEY", "\"$debugGoogleApiKey\"")
            buildConfigField("String", "BACKEND_URL", "\"$debugBackendUrl\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    packaging {
        resources {
            excludes += "META-INF/versions/9/OSGI-INF/MANIFEST.MF"
        }
    }
}

dependencies {
    implementation("io.coil-kt:coil-compose:2.3.0")
    implementation("com.google.accompanist:accompanist-permissions:0.31.5-beta")
    // Hilt
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation("com.google.dagger:hilt-android:2.51.1")
    implementation("com.android.identity:identity-doctypes-jvm:202411.1")
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("androidx.preference:preference-ktx:1.2.1")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:3.0.2")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.datastore:datastore-preferences-core:1.1.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    // AndroidX & Compose
    implementation(platform("androidx.compose:compose-bom:2024.11.00"))
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.3.1")
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("com.google.maps.android:maps-compose:6.2.1")
    implementation("com.google.accompanist:accompanist-permissions:0.33.0-alpha")
    // Navigation
    implementation("androidx.navigation:navigation-compose:2.8.4")
    implementation("androidx.navigation:navigation-runtime-ktx:2.8.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.4")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.8.4")
    androidTestImplementation("androidx.navigation:navigation-testing:2.8.4")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")

    // Retrofit & OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    implementation("androidx.compose.material3:material3:1.4.0-alpha04")
    implementation("androidx.compose.material:material-icons-extended:1.7.5")
    // Testing
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.11.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
    testImplementation("app.cash.turbine:turbine:0.12.1")
    testImplementation("org.mockito:mockito-core:5.4.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
    // Debugging tools
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    testImplementation("org.slf4j:slf4j-api:2.0.7")
    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.7")
    implementation(kotlin("test"))
}

kapt {

    correctErrorTypes = true
}
