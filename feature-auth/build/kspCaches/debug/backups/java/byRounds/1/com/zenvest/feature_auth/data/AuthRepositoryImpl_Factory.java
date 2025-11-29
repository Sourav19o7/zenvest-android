package com.zenvest.feature_auth.data;

import com.zenvest.core.network.response.ResponseHandler;
import com.zenvest.feature_auth.data.network.AuthApiService;
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
public final class AuthRepositoryImpl_Factory implements Factory<AuthRepositoryImpl> {
  private final Provider<AuthApiService> authApiServiceProvider;

  private final Provider<ResponseHandler> responseHandlerProvider;

  public AuthRepositoryImpl_Factory(Provider<AuthApiService> authApiServiceProvider,
      Provider<ResponseHandler> responseHandlerProvider) {
    this.authApiServiceProvider = authApiServiceProvider;
    this.responseHandlerProvider = responseHandlerProvider;
  }

  @Override
  public AuthRepositoryImpl get() {
    return newInstance(authApiServiceProvider.get(), responseHandlerProvider.get());
  }

  public static AuthRepositoryImpl_Factory create(Provider<AuthApiService> authApiServiceProvider,
      Provider<ResponseHandler> responseHandlerProvider) {
    return new AuthRepositoryImpl_Factory(authApiServiceProvider, responseHandlerProvider);
  }

  public static AuthRepositoryImpl newInstance(AuthApiService authApiService,
      ResponseHandler responseHandler) {
    return new AuthRepositoryImpl(authApiService, responseHandler);
  }
}
