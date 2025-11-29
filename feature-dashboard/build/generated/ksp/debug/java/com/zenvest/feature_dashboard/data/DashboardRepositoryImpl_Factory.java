package com.zenvest.feature_dashboard.data;

import com.zenvest.core.network.response.ResponseHandler;
import com.zenvest.feature_dashboard.data.network.DashboardApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class DashboardRepositoryImpl_Factory implements Factory<DashboardRepositoryImpl> {
  private final Provider<DashboardApiService> dashboardApiServiceProvider;

  private final Provider<ResponseHandler> responseHandlerProvider;

  public DashboardRepositoryImpl_Factory(Provider<DashboardApiService> dashboardApiServiceProvider,
      Provider<ResponseHandler> responseHandlerProvider) {
    this.dashboardApiServiceProvider = dashboardApiServiceProvider;
    this.responseHandlerProvider = responseHandlerProvider;
  }

  @Override
  public DashboardRepositoryImpl get() {
    return newInstance(dashboardApiServiceProvider.get(), responseHandlerProvider.get());
  }

  public static DashboardRepositoryImpl_Factory create(
      Provider<DashboardApiService> dashboardApiServiceProvider,
      Provider<ResponseHandler> responseHandlerProvider) {
    return new DashboardRepositoryImpl_Factory(dashboardApiServiceProvider, responseHandlerProvider);
  }

  public static DashboardRepositoryImpl newInstance(DashboardApiService dashboardApiService,
      ResponseHandler responseHandler) {
    return new DashboardRepositoryImpl(dashboardApiService, responseHandler);
  }
}
