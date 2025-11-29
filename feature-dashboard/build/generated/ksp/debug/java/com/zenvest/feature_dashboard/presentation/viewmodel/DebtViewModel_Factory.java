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
public final class DebtViewModel_Factory implements Factory<DebtViewModel> {
  private final Provider<DashboardRepository> dashboardRepositoryProvider;

  public DebtViewModel_Factory(Provider<DashboardRepository> dashboardRepositoryProvider) {
    this.dashboardRepositoryProvider = dashboardRepositoryProvider;
  }

  @Override
  public DebtViewModel get() {
    return newInstance(dashboardRepositoryProvider.get());
  }

  public static DebtViewModel_Factory create(
      Provider<DashboardRepository> dashboardRepositoryProvider) {
    return new DebtViewModel_Factory(dashboardRepositoryProvider);
  }

  public static DebtViewModel newInstance(DashboardRepository dashboardRepository) {
    return new DebtViewModel(dashboardRepository);
  }
}
