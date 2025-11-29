package com.zenvest.feature_auth.di;

import com.zenvest.core.network.response.ResponseHandler;
import com.zenvest.feature_auth.data.network.AuthApiService;
import com.zenvest.feature_auth.domain.AuthRepository;
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
public final class AuthModule_ProvideAuthRepositoryFactory implements Factory<AuthRepository> {
  private final Provider<AuthApiService> authApiServiceProvider;

  private final Provider<ResponseHandler> responseHandlerProvider;

  public AuthModule_ProvideAuthRepositoryFactory(Provider<AuthApiService> authApiServiceProvider,
      Provider<ResponseHandler> responseHandlerProvider) {
    this.authApiServiceProvider = authApiServiceProvider;
    this.responseHandlerProvider = responseHandlerProvider;
  }

  @Override
  public AuthRepository get() {
    return provideAuthRepository(authApiServiceProvider.get(), responseHandlerProvider.get());
  }

  public static AuthModule_ProvideAuthRepositoryFactory create(
      Provider<AuthApiService> authApiServiceProvider,
      Provider<ResponseHandler> responseHandlerProvider) {
    return new AuthModule_ProvideAuthRepositoryFactory(authApiServiceProvider, responseHandlerProvider);
  }

  public static AuthRepository provideAuthRepository(AuthApiService authApiService,
      ResponseHandler responseHandler) {
    return Preconditions.checkNotNullFromProvides(AuthModule.INSTANCE.provideAuthRepository(authApiService, responseHandler));
  }
}
