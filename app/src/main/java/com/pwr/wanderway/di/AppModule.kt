package com.pwr.wanderway.di

import android.content.Context
import com.pwr.wanderway.data.local.RoutePreferencesManager
import com.pwr.wanderway.data.local.SettingsManager
import com.pwr.wanderway.data.local.TokenManager
import com.pwr.wanderway.data.repository.AuthRepository
import com.pwr.wanderway.data.repository.RoutePreferencesRepository
import com.pwr.wanderway.data.repository.RouteRepository
import com.pwr.wanderway.data.repository.SettingsRepository
import com.pwr.wanderway.network.ApiClient
import com.pwr.wanderway.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager {
        return TokenManager(context)
    }


    @Provides
    @Singleton
    fun provideRoutePreferencesManager(@ApplicationContext context: Context): RoutePreferencesManager {
        return RoutePreferencesManager(context)
    }

    @Provides
    @Singleton
    fun provideSettingsManager(@ApplicationContext context: Context): SettingsManager {
        return SettingsManager(context)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(settingsManager: SettingsManager): SettingsRepository {
        return SettingsRepository(settingsManager)
    }

    @Provides
    @Singleton
    fun provideRoutePreferencesRepository(
        routePreferencesManager: RoutePreferencesManager
    ): RoutePreferencesRepository {
        return RoutePreferencesRepository(routePreferencesManager)
    }

    @Provides
    @Singleton
    fun provideRouteRepository(
        apiService: ApiService,
        routePreferencesManager: RoutePreferencesManager
    ): RouteRepository {
        return RouteRepository(apiService, routePreferencesManager)
    }

    @Provides
    @Singleton
    fun provideApiService(tokenManager: TokenManager): ApiService {
        return ApiClient.create(tokenManager)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        apiService: ApiService,
        tokenManager: TokenManager
    ): AuthRepository {
        return AuthRepository(tokenManager, apiService)
    }

}