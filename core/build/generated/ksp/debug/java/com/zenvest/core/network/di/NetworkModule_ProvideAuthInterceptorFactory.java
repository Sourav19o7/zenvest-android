package com.zenvest.core.network.di;

import com.zenvest.core.common.session.SessionManager;
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
public final class NetworkModule_ProvideAuthInterceptorFactory implements Factory<AuthInterceptor> {
  private final Provider<SessionManager> sessionManagerProvider;

  public NetworkModule_ProvideAuthInterceptorFactory(
      Provider<SessionManager> sessionManagerProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public AuthInterceptor get() {
    return provideAuthInterceptor(sessionManagerProvider.get());
  }

  public static NetworkModule_ProvideAuthInterceptorFactory create(
      Provider<SessionManager> sessionManagerProvider) {
    return new NetworkModule_ProvideAuthInterceptorFactory(sessionManagerProvider);
  }

  public static AuthInterceptor provideAuthInterceptor(SessionManager sessionManager) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideAuthInterceptor(sessionManager));
  }
}
