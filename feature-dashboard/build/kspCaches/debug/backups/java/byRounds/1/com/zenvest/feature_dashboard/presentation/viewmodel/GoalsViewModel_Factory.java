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
public final class GoalsViewModel_Factory implements Factory<GoalsViewModel> {
  private final Provider<DashboardRepository> dashboardRepositoryProvider;

  public GoalsViewModel_Factory(Provider<DashboardRepository> dashboardRepositoryProvider) {
    this.dashboardRepositoryProvider = dashboardRepositoryProvider;
  }

  @Override
  public GoalsViewModel get() {
    return newInstance(dashboardRepositoryProvider.get());
  }

  public static GoalsViewModel_Factory create(
      Provider<DashboardRepository> dashboardRepositoryProvider) {
    return new GoalsViewModel_Factory(dashboardRepositoryProvider);
  }

  public static GoalsViewModel newInstance(DashboardRepository dashboardRepository) {
    return new GoalsViewModel(dashboardRepository);
  }
}
