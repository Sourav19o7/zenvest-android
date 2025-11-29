package com.zenvest.feature_auth.presentation.viewmodel;

import com.zenvest.core.common.session.SessionManager;
import com.zenvest.feature_auth.domain.AuthRepository;
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
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  public AuthViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public AuthViewModel get() {
    return newInstance(authRepositoryProvider.get(), sessionManagerProvider.get());
  }

  public static AuthViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    return new AuthViewModel_Factory(authRepositoryProvider, sessionManagerProvider);
  }

  public static AuthViewModel newInstance(AuthRepository authRepository,
      SessionManager sessionManager) {
    return new AuthViewModel(authRepository, sessionManager);
  }
}
