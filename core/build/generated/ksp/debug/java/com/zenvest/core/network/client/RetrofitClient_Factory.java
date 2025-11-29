package com.zenvest.core.network.client;

import com.zenvest.core.network.config.NetworkConfig;
import com.zenvest.core.network.interceptors.AuthInterceptor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class RetrofitClient_Factory implements Factory<RetrofitClient> {
  private final Provider<NetworkConfig> configProvider;

  private final Provider<AuthInterceptor> authInterceptorProvider;

  public RetrofitClient_Factory(Provider<NetworkConfig> configProvider,
      Provider<AuthInterceptor> authInterceptorProvider) {
    this.configProvider = configProvider;
    this.authInterceptorProvider = authInterceptorProvider;
  }

  @Override
  public RetrofitClient get() {
    return newInstance(configProvider.get(), authInterceptorProvider.get());
  }

  public static RetrofitClient_Factory create(Provider<NetworkConfig> configProvider,
      Provider<AuthInterceptor> authInterceptorProvider) {
    return new RetrofitClient_Factory(configProvider, authInterceptorProvider);
  }

  public static RetrofitClient newInstance(NetworkConfig config, AuthInterceptor authInterceptor) {
    return new RetrofitClient(config, authInterceptor);
  }
}
