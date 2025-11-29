package com.zenvest.feature_onboarding.di;

import com.zenvest.core.network.client.RetrofitClient;
import com.zenvest.feature_onboarding.data.network.OnboardingApiService;
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
public final class OnboardingModule_ProvideOnboardingApiServiceFactory implements Factory<OnboardingApiService> {
  private final Provider<RetrofitClient> retrofitClientProvider;

  public OnboardingModule_ProvideOnboardingApiServiceFactory(
      Provider<RetrofitClient> retrofitClientProvider) {
    this.retrofitClientProvider = retrofitClientProvider;
  }

  @Override
  public OnboardingApiService get() {
    return provideOnboardingApiService(retrofitClientProvider.get());
  }

  public static OnboardingModule_ProvideOnboardingApiServiceFactory create(
      Provider<RetrofitClient> retrofitClientProvider) {
    return new OnboardingModule_ProvideOnboardingApiServiceFactory(retrofitClientProvider);
  }

  public static OnboardingApiService provideOnboardingApiService(RetrofitClient retrofitClient) {
    return Preconditions.checkNotNullFromProvides(OnboardingModule.INSTANCE.provideOnboardingApiService(retrofitClient));
  }
}
