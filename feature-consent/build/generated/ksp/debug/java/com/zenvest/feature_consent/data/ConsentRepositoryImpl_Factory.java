package com.zenvest.feature_consent.data;

import com.zenvest.core.network.response.ResponseHandler;
import com.zenvest.feature_consent.data.network.ConsentApiService;
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
public final class ConsentRepositoryImpl_Factory implements Factory<ConsentRepositoryImpl> {
  private final Provider<ConsentApiService> consentApiServiceProvider;

  private final Provider<ResponseHandler> responseHandlerProvider;

  public ConsentRepositoryImpl_Factory(Provider<ConsentApiService> consentApiServiceProvider,
      Provider<ResponseHandler> responseHandlerProvider) {
    this.consentApiServiceProvider = consentApiServiceProvider;
    this.responseHandlerProvider = responseHandlerProvider;
  }

  @Override
  public ConsentRepositoryImpl get() {
    return newInstance(consentApiServiceProvider.get(), responseHandlerProvider.get());
  }

  public static ConsentRepositoryImpl_Factory create(
      Provider<ConsentApiService> consentApiServiceProvider,
      Provider<ResponseHandler> responseHandlerProvider) {
    return new ConsentRepositoryImpl_Factory(consentApiServiceProvider, responseHandlerProvider);
  }

  public static ConsentRepositoryImpl newInstance(ConsentApiService consentApiService,
      ResponseHandler responseHandler) {
    return new ConsentRepositoryImpl(consentApiService, responseHandler);
  }
}
