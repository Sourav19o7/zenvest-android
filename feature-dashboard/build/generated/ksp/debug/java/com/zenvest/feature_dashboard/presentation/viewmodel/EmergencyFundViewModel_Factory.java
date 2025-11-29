package com.zenvest.feature_dashboard.presentation.viewmodel;

import com.zenvest.feature_dashboard.domain.DashboardRepository;
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
public final class EmergencyFundViewModel_Factory implements Factory<EmergencyFundViewModel> {
  private final Provider<DashboardRepository> dashboardRepositoryProvider;

  public EmergencyFundViewModel_Factory(Provider<DashboardRepository> dashboardRepositoryProvider) {
    this.dashboardRepositoryProvider = dashboardRepositoryProvider;
  }

  @Override
  public EmergencyFundViewModel get() {
    return newInstance(dashboardRepositoryProvider.get());
  }

  public static EmergencyFundViewModel_Factory create(
      Provider<DashboardRepository> dashboardRepositoryProvider) {
    return new EmergencyFundViewModel_Factory(dashboardRepositoryProvider);
  }

  public static EmergencyFundViewModel newInstance(DashboardRepository dashboardRepository) {
    return new EmergencyFundViewModel(dashboardRepository);
  }
}
