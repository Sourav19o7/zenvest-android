package com.zenvest.feature_auth.di;

import com.zenvest.core.network.client.RetrofitClient;
import com.zenvest.feature_auth.data.network.AuthApiService;
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
public final class AuthModule_ProvideAuthApiServiceFactory implements Factory<AuthApiService> {
  private final Provider<RetrofitClient> retrofitClientProvider;

  public AuthModule_ProvideAuthApiServiceFactory(Provider<RetrofitClient> retrofitClientProvider) {
    this.retrofitClientProvider = retrofitClientProvider;
  }

  @Override
  public AuthApiService get() {
    return provideAuthApiService(retrofitClientProvider.get());
  }

  public static AuthModule_ProvideAuthApiServiceFactory create(
      Provider<RetrofitClient> retrofitClientProvider) {
    return new AuthModule_ProvideAuthApiServiceFactory(retrofitClientProvider);
  }

  public static AuthApiService provideAuthApiService(RetrofitClient retrofitClient) {
    return Preconditions.checkNotNullFromProvides(AuthModule.INSTANCE.provideAuthApiService(retrofitClient));
  }
}
