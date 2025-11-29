package com.zenvest.feature_dashboard.presentation.viewmodel;

import com.zenvest.core.common.session.SessionManager;
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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<DashboardRepository> dashboardRepositoryProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  public DashboardViewModel_Factory(Provider<DashboardRepository> dashboardRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    this.dashboardRepositoryProvider = dashboardRepositoryProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(dashboardRepositoryProvider.get(), sessionManagerProvider.get());
  }

  public static DashboardViewModel_Factory create(
      Provider<DashboardRepository> dashboardRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    return new DashboardViewModel_Factory(dashboardRepositoryProvider, sessionManagerProvider);
  }

  public static DashboardViewModel newInstance(DashboardRepository dashboardRepository,
      SessionManager sessionManager) {
    return new DashboardViewModel(dashboardRepository, sessionManager);
  }
}
