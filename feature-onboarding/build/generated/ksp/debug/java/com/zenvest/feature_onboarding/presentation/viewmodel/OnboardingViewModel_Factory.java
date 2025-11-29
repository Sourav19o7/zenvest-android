package com.zenvest.feature_onboarding.presentation.viewmodel;

import com.zenvest.core.common.session.SessionManager;
import com.zenvest.feature_onboarding.domain.OnboardingRepository;
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
public final class OnboardingViewModel_Factory implements Factory<OnboardingViewModel> {
  private final Provider<OnboardingRepository> onboardingRepositoryProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  public OnboardingViewModel_Factory(Provider<OnboardingRepository> onboardingRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    this.onboardingRepositoryProvider = onboardingRepositoryProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public OnboardingViewModel get() {
    return newInstance(onboardingRepositoryProvider.get(), sessionManagerProvider.get());
  }

  public static OnboardingViewModel_Factory create(
      Provider<OnboardingRepository> onboardingRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    return new OnboardingViewModel_Factory(onboardingRepositoryProvider, sessionManagerProvider);
  }

  public static OnboardingViewModel newInstance(OnboardingRepository onboardingRepository,
      SessionManager sessionManager) {
    return new OnboardingViewModel(onboardingRepository, sessionManager);
  }
}
