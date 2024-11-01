package com.pwr.wanderway.di

import android.content.Context
import com.pwr.wanderway.data.local.TokenManager
import com.pwr.wanderway.data.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTokenManager(context: Context): TokenManager {
        return TokenManager(context)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(tokenManager: TokenManager): AuthRepository {
        return AuthRepository(tokenManager)
    }
}
