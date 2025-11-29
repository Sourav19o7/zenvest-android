package com.zenvest.feature_consent.presentation.viewmodel;

import com.zenvest.core.common.session.SessionManager;
import com.zenvest.feature_consent.domain.ConsentRepository;
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
public final class ConsentViewModel_Factory implements Factory<ConsentViewModel> {
  private final Provider<ConsentRepository> consentRepositoryProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  public ConsentViewModel_Factory(Provider<ConsentRepository> consentRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    this.consentRepositoryProvider = consentRepositoryProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public ConsentViewModel get() {
    return newInstance(consentRepositoryProvider.get(), sessionManagerProvider.get());
  }

  public static ConsentViewModel_Factory create(
      Provider<ConsentRepository> consentRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    return new ConsentViewModel_Factory(consentRepositoryProvider, sessionManagerProvider);
  }

  public static ConsentViewModel newInstance(ConsentRepository consentRepository,
      SessionManager sessionManager) {
    return new ConsentViewModel(consentRepository, sessionManager);
  }
}
