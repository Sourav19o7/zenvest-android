package com.zenvest.feature_auth.di

import com.zenvest.core.network.client.RetrofitClient
import com.zenvest.core.network.response.ResponseHandler
import com.zenvest.feature_auth.data.AuthRepositoryImpl
import com.zenvest.feature_auth.data.network.AuthApiService
import com.zenvest.feature_auth.domain.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AuthModule {

    @Provides
    @ViewModelScoped
    fun provideAuthApiService(retrofitClient: RetrofitClient): AuthApiService {
        return retrofitClient.createService()
    }

    @Provides
    @ViewModelScoped
    fun provideAuthRepository(
        authApiService: AuthApiService,
        responseHandler: ResponseHandler
    ): AuthRepository {
        return AuthRepositoryImpl(authApiService, responseHandler)
    }
}
