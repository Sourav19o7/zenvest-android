package com.zenvest.feature_consent.di

import com.zenvest.core.network.client.RetrofitClient
import com.zenvest.core.network.response.ResponseHandler
import com.zenvest.feature_consent.data.ConsentRepositoryImpl
import com.zenvest.feature_consent.data.network.ConsentApiService
import com.zenvest.feature_consent.domain.ConsentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ConsentModule {

    @Provides
    @ViewModelScoped
    fun provideConsentApiService(retrofitClient: RetrofitClient): ConsentApiService {
        return retrofitClient.createService()
    }

    @Provides
    @ViewModelScoped
    fun provideConsentRepository(
        consentApiService: ConsentApiService,
        responseHandler: ResponseHandler
    ): ConsentRepository {
        return ConsentRepositoryImpl(consentApiService, responseHandler)
    }
}
