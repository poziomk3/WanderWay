package com.pwr.wanderway

import android.app.Application
import com.pwr.wanderway.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WanderWayApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WanderWayApp)
            modules(appModule)
        }
    }
}