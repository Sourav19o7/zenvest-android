package com.zenvest.feature_dashboard.di;

import com.zenvest.core.network.response.ResponseHandler;
import com.zenvest.feature_dashboard.data.network.DashboardApiService;
import com.zenvest.feature_dashboard.domain.DashboardRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("dagger.hilt.android.scopes.ViewModelScoped")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class DashboardModule_ProvideDashboardRepositoryFactory implements Factory<DashboardRepository> {
  private final Provider<DashboardApiService> dashboardApiServiceProvider;

  private final Provider<ResponseHandler> responseHandlerProvider;

  public DashboardModule_ProvideDashboardRepositoryFactory(
      Provider<DashboardApiService> dashboardApiServiceProvider,
      Provider<ResponseHandler> responseHandlerProvider) {
    this.dashboardApiServiceProvider = dashboardApiServiceProvider;
    this.responseHandlerProvider = responseHandlerProvider;
  }

  @Override
  public DashboardRepository get() {
    return provideDashboardRepository(dashboardApiServiceProvider.get(), responseHandlerProvider.get());
  }

  public static DashboardModule_ProvideDashboardRepositoryFactory create(
      Provider<DashboardApiService> dashboardApiServiceProvider,
      Provider<ResponseHandler> responseHandlerProvider) {
    return new DashboardModule_ProvideDashboardRepositoryFactory(dashboardApiServiceProvider, responseHandlerProvider);
  }

  public static DashboardRepository provideDashboardRepository(
      DashboardApiService dashboardApiService, ResponseHandler responseHandler) {
    return Preconditions.checkNotNullFromProvides(DashboardModule.INSTANCE.provideDashboardRepository(dashboardApiService, responseHandler));
  }
}
