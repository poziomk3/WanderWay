package com.pwr.wanderway.di

import android.content.Context
import com.pwr.wanderway.data.local.PreferencesManager
import com.pwr.wanderway.data.local.TokenManager
import com.pwr.wanderway.data.repository.AuthRepository
import com.pwr.wanderway.data.repository.RouteRepository
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
    fun providePreferencesManager(@ApplicationContext context: Context): PreferencesManager {
        return PreferencesManager(context)
    }

    @Provides
    @Singleton
    fun provideRouteRepository(apiService: ApiService): RouteRepository {
        return RouteRepository(apiService)
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