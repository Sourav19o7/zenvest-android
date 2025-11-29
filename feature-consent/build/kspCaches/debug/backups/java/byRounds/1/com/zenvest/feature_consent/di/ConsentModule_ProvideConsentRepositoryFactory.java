package com.zenvest.feature_consent.di;

import com.zenvest.core.network.response.ResponseHandler;
import com.zenvest.feature_consent.data.network.ConsentApiService;
import com.zenvest.feature_consent.domain.ConsentRepository;
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
public final class ConsentModule_ProvideConsentRepositoryFactory implements Factory<ConsentRepository> {
  private final Provider<ConsentApiService> consentApiServiceProvider;

  private final Provider<ResponseHandler> responseHandlerProvider;

  public ConsentModule_ProvideConsentRepositoryFactory(
      Provider<ConsentApiService> consentApiServiceProvider,
      Provider<ResponseHandler> responseHandlerProvider) {
    this.consentApiServiceProvider = consentApiServiceProvider;
    this.responseHandlerProvider = responseHandlerProvider;
  }

  @Override
  public ConsentRepository get() {
    return provideConsentRepository(consentApiServiceProvider.get(), responseHandlerProvider.get());
  }

  public static ConsentModule_ProvideConsentRepositoryFactory create(
      Provider<ConsentApiService> consentApiServiceProvider,
      Provider<ResponseHandler> responseHandlerProvider) {
    return new ConsentModule_ProvideConsentRepositoryFactory(consentApiServiceProvider, responseHandlerProvider);
  }

  public static ConsentRepository provideConsentRepository(ConsentApiService consentApiService,
      ResponseHandler responseHandler) {
    return Preconditions.checkNotNullFromProvides(ConsentModule.INSTANCE.provideConsentRepository(consentApiService, responseHandler));
  }
}
