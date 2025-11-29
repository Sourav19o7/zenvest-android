package com.zenvest.feature_consent.di;

import com.zenvest.core.network.client.RetrofitClient;
import com.zenvest.feature_consent.data.network.ConsentApiService;
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
public final class ConsentModule_ProvideConsentApiServiceFactory implements Factory<ConsentApiService> {
  private final Provider<RetrofitClient> retrofitClientProvider;

  public ConsentModule_ProvideConsentApiServiceFactory(
      Provider<RetrofitClient> retrofitClientProvider) {
    this.retrofitClientProvider = retrofitClientProvider;
  }

  @Override
  public ConsentApiService get() {
    return provideConsentApiService(retrofitClientProvider.get());
  }

  public static ConsentModule_ProvideConsentApiServiceFactory create(
      Provider<RetrofitClient> retrofitClientProvider) {
    return new ConsentModule_ProvideConsentApiServiceFactory(retrofitClientProvider);
  }

  public static ConsentApiService provideConsentApiService(RetrofitClient retrofitClient) {
    return Preconditions.checkNotNullFromProvides(ConsentModule.INSTANCE.provideConsentApiService(retrofitClient));
  }
}
