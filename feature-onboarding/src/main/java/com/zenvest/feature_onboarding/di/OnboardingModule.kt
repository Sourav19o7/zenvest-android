package com.zenvest.feature_onboarding.di

import com.zenvest.core.network.client.RetrofitClient
import com.zenvest.core.network.response.ResponseHandler
import com.zenvest.feature_onboarding.data.OnboardingRepositoryImpl
import com.zenvest.feature_onboarding.data.network.OnboardingApiService
import com.zenvest.feature_onboarding.domain.OnboardingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OnboardingModule {

    @Provides
    @ViewModelScoped
    fun provideOnboardingApiService(retrofitClient: RetrofitClient): OnboardingApiService {
        return retrofitClient.createService()
    }

    @Provides
    @ViewModelScoped
    fun provideOnboardingRepository(
        onboardingApiService: OnboardingApiService,
        responseHandler: ResponseHandler
    ): OnboardingRepository {
        return OnboardingRepositoryImpl(onboardingApiService, responseHandler)
    }
}
