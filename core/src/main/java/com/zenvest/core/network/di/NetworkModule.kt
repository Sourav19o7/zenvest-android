package com.zenvest.core.network.di

import com.zenvest.core.common.session.SessionManager
import com.zenvest.core.network.client.RetrofitClient
import com.zenvest.core.network.config.NetworkConfig
import com.zenvest.core.network.interceptors.AuthInterceptor
import com.zenvest.core.network.response.ResponseHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkConfig(): NetworkConfig {
        return NetworkConfig(
//            baseUrl = "https://api.zenvest.in/",
//            baseUrl = "http://10.0.2.2:3000/",  // For emulator
            baseUrl = "http://localhost:3000/",  // For real device via adb reverse
            isDebug = true
        )
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(sessionManager: SessionManager): AuthInterceptor {
        return AuthInterceptor(sessionManager)
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(
        config: NetworkConfig,
        authInterceptor: AuthInterceptor
    ): RetrofitClient {
        return RetrofitClient(config, authInterceptor)
    }

    @Provides
    @Singleton
    fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler()
    }
}
