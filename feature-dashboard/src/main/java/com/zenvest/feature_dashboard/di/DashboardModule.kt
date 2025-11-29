package com.zenvest.feature_dashboard.di

import com.zenvest.core.network.client.RetrofitClient
import com.zenvest.core.network.response.ResponseHandler
import com.zenvest.feature_dashboard.data.DashboardRepositoryImpl
import com.zenvest.feature_dashboard.data.network.DashboardApiService
import com.zenvest.feature_dashboard.domain.DashboardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DashboardModule {

    @Provides
    @ViewModelScoped
    fun provideDashboardApiService(retrofitClient: RetrofitClient): DashboardApiService {
        return retrofitClient.createService()
    }

    @Provides
    @ViewModelScoped
    fun provideDashboardRepository(
        dashboardApiService: DashboardApiService,
        responseHandler: ResponseHandler
    ): DashboardRepository {
        return DashboardRepositoryImpl(dashboardApiService, responseHandler)
    }
}
