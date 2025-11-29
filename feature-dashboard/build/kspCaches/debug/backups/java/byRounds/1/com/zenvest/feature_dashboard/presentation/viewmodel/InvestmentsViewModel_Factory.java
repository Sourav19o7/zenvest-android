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
public final class InvestmentsViewModel_Factory implements Factory<InvestmentsViewModel> {
  private final Provider<DashboardRepository> dashboardRepositoryProvider;

  public InvestmentsViewModel_Factory(Provider<DashboardRepository> dashboardRepositoryProvider) {
    this.dashboardRepositoryProvider = dashboardRepositoryProvider;
  }

  @Override
  public InvestmentsViewModel get() {
    return newInstance(dashboardRepositoryProvider.get());
  }

  public static InvestmentsViewModel_Factory create(
      Provider<DashboardRepository> dashboardRepositoryProvider) {
    return new InvestmentsViewModel_Factory(dashboardRepositoryProvider);
  }

  public static InvestmentsViewModel newInstance(DashboardRepository dashboardRepository) {
    return new InvestmentsViewModel(dashboardRepository);
  }
}
