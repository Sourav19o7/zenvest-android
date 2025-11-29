package com.zenvest.core.network.di;

import com.zenvest.core.network.client.RetrofitClient;
import com.zenvest.core.network.config.NetworkConfig;
import com.zenvest.core.network.interceptors.AuthInterceptor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class NetworkModule_ProvideRetrofitClientFactory implements Factory<RetrofitClient> {
  private final Provider<NetworkConfig> configProvider;

  private final Provider<AuthInterceptor> authInterceptorProvider;

  public NetworkModule_ProvideRetrofitClientFactory(Provider<NetworkConfig> configProvider,
      Provider<AuthInterceptor> authInterceptorProvider) {
    this.configProvider = configProvider;
    this.authInterceptorProvider = authInterceptorProvider;
  }

  @Override
  public RetrofitClient get() {
    return provideRetrofitClient(configProvider.get(), authInterceptorProvider.get());
  }

  public static NetworkModule_ProvideRetrofitClientFactory create(
      Provider<NetworkConfig> configProvider, Provider<AuthInterceptor> authInterceptorProvider) {
    return new NetworkModule_ProvideRetrofitClientFactory(configProvider, authInterceptorProvider);
  }

  public static RetrofitClient provideRetrofitClient(NetworkConfig config,
      AuthInterceptor authInterceptor) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideRetrofitClient(config, authInterceptor));
  }
}
