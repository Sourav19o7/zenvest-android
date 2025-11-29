package com.zenvest.feature_onboarding.di;

import com.zenvest.core.network.response.ResponseHandler;
import com.zenvest.feature_onboarding.data.network.OnboardingApiService;
import com.zenvest.feature_onboarding.domain.OnboardingRepository;
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
public final class OnboardingModule_ProvideOnboardingRepositoryFactory implements Factory<OnboardingRepository> {
  private final Provider<OnboardingApiService> onboardingApiServiceProvider;

  private final Provider<ResponseHandler> responseHandlerProvider;

  public OnboardingModule_ProvideOnboardingRepositoryFactory(
      Provider<OnboardingApiService> onboardingApiServiceProvider,
      Provider<ResponseHandler> responseHandlerProvider) {
    this.onboardingApiServiceProvider = onboardingApiServiceProvider;
    this.responseHandlerProvider = responseHandlerProvider;
  }

  @Override
  public OnboardingRepository get() {
    return provideOnboardingRepository(onboardingApiServiceProvider.get(), responseHandlerProvider.get());
  }

  public static OnboardingModule_ProvideOnboardingRepositoryFactory create(
      Provider<OnboardingApiService> onboardingApiServiceProvider,
      Provider<ResponseHandler> responseHandlerProvider) {
    return new OnboardingModule_ProvideOnboardingRepositoryFactory(onboardingApiServiceProvider, responseHandlerProvider);
  }

  public static OnboardingRepository provideOnboardingRepository(
      OnboardingApiService onboardingApiService, ResponseHandler responseHandler) {
    return Preconditions.checkNotNullFromProvides(OnboardingModule.INSTANCE.provideOnboardingRepository(onboardingApiService, responseHandler));
  }
}
