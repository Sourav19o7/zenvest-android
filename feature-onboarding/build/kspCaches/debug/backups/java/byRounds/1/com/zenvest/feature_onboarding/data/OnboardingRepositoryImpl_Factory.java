package com.zenvest.feature_onboarding.data;

import com.zenvest.core.network.response.ResponseHandler;
import com.zenvest.feature_onboarding.data.network.OnboardingApiService;
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
public final class OnboardingRepositoryImpl_Factory implements Factory<OnboardingRepositoryImpl> {
  private final Provider<OnboardingApiService> onboardingApiServiceProvider;

  private final Provider<ResponseHandler> responseHandlerProvider;

  public OnboardingRepositoryImpl_Factory(
      Provider<OnboardingApiService> onboardingApiServiceProvider,
      Provider<ResponseHandler> responseHandlerProvider) {
    this.onboardingApiServiceProvider = onboardingApiServiceProvider;
    this.responseHandlerProvider = responseHandlerProvider;
  }

  @Override
  public OnboardingRepositoryImpl get() {
    return newInstance(onboardingApiServiceProvider.get(), responseHandlerProvider.get());
  }

  public static OnboardingRepositoryImpl_Factory create(
      Provider<OnboardingApiService> onboardingApiServiceProvider,
      Provider<ResponseHandler> responseHandlerProvider) {
    return new OnboardingRepositoryImpl_Factory(onboardingApiServiceProvider, responseHandlerProvider);
  }

  public static OnboardingRepositoryImpl newInstance(OnboardingApiService onboardingApiService,
      ResponseHandler responseHandler) {
    return new OnboardingRepositoryImpl(onboardingApiService, responseHandler);
  }
}
