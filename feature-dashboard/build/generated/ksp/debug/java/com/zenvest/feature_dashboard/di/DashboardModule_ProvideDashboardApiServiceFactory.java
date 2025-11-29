package com.zenvest.feature_dashboard.di;

import com.zenvest.core.network.client.RetrofitClient;
import com.zenvest.feature_dashboard.data.network.DashboardApiService;
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
public final class DashboardModule_ProvideDashboardApiServiceFactory implements Factory<DashboardApiService> {
  private final Provider<RetrofitClient> retrofitClientProvider;

  public DashboardModule_ProvideDashboardApiServiceFactory(
      Provider<RetrofitClient> retrofitClientProvider) {
    this.retrofitClientProvider = retrofitClientProvider;
  }

  @Override
  public DashboardApiService get() {
    return provideDashboardApiService(retrofitClientProvider.get());
  }

  public static DashboardModule_ProvideDashboardApiServiceFactory create(
      Provider<RetrofitClient> retrofitClientProvider) {
    return new DashboardModule_ProvideDashboardApiServiceFactory(retrofitClientProvider);
  }

  public static DashboardApiService provideDashboardApiService(RetrofitClient retrofitClient) {
    return Preconditions.checkNotNullFromProvides(DashboardModule.INSTANCE.provideDashboardApiService(retrofitClient));
  }
}
