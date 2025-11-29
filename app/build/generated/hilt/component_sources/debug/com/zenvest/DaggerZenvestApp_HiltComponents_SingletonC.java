package com.zenvest;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.zenvest.core.common.session.SessionManager;
import com.zenvest.core.common.session.SessionModule_ProvideSessionManagerFactory;
import com.zenvest.core.network.client.RetrofitClient;
import com.zenvest.core.network.config.NetworkConfig;
import com.zenvest.core.network.di.NetworkModule_ProvideAuthInterceptorFactory;
import com.zenvest.core.network.di.NetworkModule_ProvideNetworkConfigFactory;
import com.zenvest.core.network.di.NetworkModule_ProvideResponseHandlerFactory;
import com.zenvest.core.network.di.NetworkModule_ProvideRetrofitClientFactory;
import com.zenvest.core.network.interceptors.AuthInterceptor;
import com.zenvest.core.network.response.ResponseHandler;
import com.zenvest.feature_auth.data.network.AuthApiService;
import com.zenvest.feature_auth.di.AuthModule_ProvideAuthApiServiceFactory;
import com.zenvest.feature_auth.di.AuthModule_ProvideAuthRepositoryFactory;
import com.zenvest.feature_auth.domain.AuthRepository;
import com.zenvest.feature_auth.presentation.viewmodel.AuthViewModel;
import com.zenvest.feature_auth.presentation.viewmodel.AuthViewModel_HiltModules;
import com.zenvest.feature_auth.presentation.viewmodel.AuthViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.zenvest.feature_auth.presentation.viewmodel.AuthViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.zenvest.feature_consent.data.network.ConsentApiService;
import com.zenvest.feature_consent.di.ConsentModule_ProvideConsentApiServiceFactory;
import com.zenvest.feature_consent.di.ConsentModule_ProvideConsentRepositoryFactory;
import com.zenvest.feature_consent.domain.ConsentRepository;
import com.zenvest.feature_consent.presentation.viewmodel.ConsentViewModel;
import com.zenvest.feature_consent.presentation.viewmodel.ConsentViewModel_HiltModules;
import com.zenvest.feature_consent.presentation.viewmodel.ConsentViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.zenvest.feature_consent.presentation.viewmodel.ConsentViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.zenvest.feature_dashboard.data.network.DashboardApiService;
import com.zenvest.feature_dashboard.di.DashboardModule_ProvideDashboardApiServiceFactory;
import com.zenvest.feature_dashboard.di.DashboardModule_ProvideDashboardRepositoryFactory;
import com.zenvest.feature_dashboard.domain.DashboardRepository;
import com.zenvest.feature_dashboard.presentation.viewmodel.DashboardViewModel;
import com.zenvest.feature_dashboard.presentation.viewmodel.DashboardViewModel_HiltModules;
import com.zenvest.feature_dashboard.presentation.viewmodel.DashboardViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.zenvest.feature_dashboard.presentation.viewmodel.DashboardViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.zenvest.feature_dashboard.presentation.viewmodel.DebtViewModel;
import com.zenvest.feature_dashboard.presentation.viewmodel.DebtViewModel_HiltModules;
import com.zenvest.feature_dashboard.presentation.viewmodel.DebtViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.zenvest.feature_dashboard.presentation.viewmodel.DebtViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.zenvest.feature_dashboard.presentation.viewmodel.EmergencyFundViewModel;
import com.zenvest.feature_dashboard.presentation.viewmodel.EmergencyFundViewModel_HiltModules;
import com.zenvest.feature_dashboard.presentation.viewmodel.EmergencyFundViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.zenvest.feature_dashboard.presentation.viewmodel.EmergencyFundViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.zenvest.feature_dashboard.presentation.viewmodel.GoalsViewModel;
import com.zenvest.feature_dashboard.presentation.viewmodel.GoalsViewModel_HiltModules;
import com.zenvest.feature_dashboard.presentation.viewmodel.GoalsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.zenvest.feature_dashboard.presentation.viewmodel.GoalsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.zenvest.feature_dashboard.presentation.viewmodel.InvestmentsViewModel;
import com.zenvest.feature_dashboard.presentation.viewmodel.InvestmentsViewModel_HiltModules;
import com.zenvest.feature_dashboard.presentation.viewmodel.InvestmentsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.zenvest.feature_dashboard.presentation.viewmodel.InvestmentsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.zenvest.feature_onboarding.data.network.OnboardingApiService;
import com.zenvest.feature_onboarding.di.OnboardingModule_ProvideOnboardingApiServiceFactory;
import com.zenvest.feature_onboarding.di.OnboardingModule_ProvideOnboardingRepositoryFactory;
import com.zenvest.feature_onboarding.domain.OnboardingRepository;
import com.zenvest.feature_onboarding.presentation.viewmodel.OnboardingViewModel;
import com.zenvest.feature_onboarding.presentation.viewmodel.OnboardingViewModel_HiltModules;
import com.zenvest.feature_onboarding.presentation.viewmodel.OnboardingViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.zenvest.feature_onboarding.presentation.viewmodel.OnboardingViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.zenvest.ui.viewmodel.SplashViewModel;
import com.zenvest.ui.viewmodel.SplashViewModel_HiltModules;
import com.zenvest.ui.viewmodel.SplashViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.zenvest.ui.viewmodel.SplashViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerZenvestApp_HiltComponents_SingletonC {
  private DaggerZenvestApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public ZenvestApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements ZenvestApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public ZenvestApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements ZenvestApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public ZenvestApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements ZenvestApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public ZenvestApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements ZenvestApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public ZenvestApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements ZenvestApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public ZenvestApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements ZenvestApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public ZenvestApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements ZenvestApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public ZenvestApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends ZenvestApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends ZenvestApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends ZenvestApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends ZenvestApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity arg0) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(9).put(AuthViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, AuthViewModel_HiltModules.KeyModule.provide()).put(ConsentViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ConsentViewModel_HiltModules.KeyModule.provide()).put(DashboardViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, DashboardViewModel_HiltModules.KeyModule.provide()).put(DebtViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, DebtViewModel_HiltModules.KeyModule.provide()).put(EmergencyFundViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, EmergencyFundViewModel_HiltModules.KeyModule.provide()).put(GoalsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, GoalsViewModel_HiltModules.KeyModule.provide()).put(InvestmentsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, InvestmentsViewModel_HiltModules.KeyModule.provide()).put(OnboardingViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, OnboardingViewModel_HiltModules.KeyModule.provide()).put(SplashViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SplashViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends ZenvestApp_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AuthApiService> provideAuthApiServiceProvider;

    private Provider<AuthRepository> provideAuthRepositoryProvider;

    private Provider<AuthViewModel> authViewModelProvider;

    private Provider<ConsentApiService> provideConsentApiServiceProvider;

    private Provider<ConsentRepository> provideConsentRepositoryProvider;

    private Provider<ConsentViewModel> consentViewModelProvider;

    private Provider<DashboardApiService> provideDashboardApiServiceProvider;

    private Provider<DashboardRepository> provideDashboardRepositoryProvider;

    private Provider<DashboardViewModel> dashboardViewModelProvider;

    private Provider<DebtViewModel> debtViewModelProvider;

    private Provider<EmergencyFundViewModel> emergencyFundViewModelProvider;

    private Provider<GoalsViewModel> goalsViewModelProvider;

    private Provider<InvestmentsViewModel> investmentsViewModelProvider;

    private Provider<OnboardingApiService> provideOnboardingApiServiceProvider;

    private Provider<OnboardingRepository> provideOnboardingRepositoryProvider;

    private Provider<OnboardingViewModel> onboardingViewModelProvider;

    private Provider<SplashViewModel> splashViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.provideAuthApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<AuthApiService>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2));
      this.provideAuthRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AuthRepository>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1));
      this.authViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.provideConsentApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<ConsentApiService>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5));
      this.provideConsentRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<ConsentRepository>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4));
      this.consentViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.provideDashboardApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<DashboardApiService>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8));
      this.provideDashboardRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<DashboardRepository>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7));
      this.dashboardViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.debtViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
      this.emergencyFundViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 10);
      this.goalsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 11);
      this.investmentsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 12);
      this.provideOnboardingApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<OnboardingApiService>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 15));
      this.provideOnboardingRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<OnboardingRepository>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 14));
      this.onboardingViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 13);
      this.splashViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 16);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(9).put(AuthViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) authViewModelProvider)).put(ConsentViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) consentViewModelProvider)).put(DashboardViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) dashboardViewModelProvider)).put(DebtViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) debtViewModelProvider)).put(EmergencyFundViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) emergencyFundViewModelProvider)).put(GoalsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) goalsViewModelProvider)).put(InvestmentsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) investmentsViewModelProvider)).put(OnboardingViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) onboardingViewModelProvider)).put(SplashViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) splashViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.zenvest.feature_auth.presentation.viewmodel.AuthViewModel 
          return (T) new AuthViewModel(viewModelCImpl.provideAuthRepositoryProvider.get(), singletonCImpl.provideSessionManagerProvider.get());

          case 1: // com.zenvest.feature_auth.domain.AuthRepository 
          return (T) AuthModule_ProvideAuthRepositoryFactory.provideAuthRepository(viewModelCImpl.provideAuthApiServiceProvider.get(), singletonCImpl.provideResponseHandlerProvider.get());

          case 2: // com.zenvest.feature_auth.data.network.AuthApiService 
          return (T) AuthModule_ProvideAuthApiServiceFactory.provideAuthApiService(singletonCImpl.provideRetrofitClientProvider.get());

          case 3: // com.zenvest.feature_consent.presentation.viewmodel.ConsentViewModel 
          return (T) new ConsentViewModel(viewModelCImpl.provideConsentRepositoryProvider.get(), singletonCImpl.provideSessionManagerProvider.get());

          case 4: // com.zenvest.feature_consent.domain.ConsentRepository 
          return (T) ConsentModule_ProvideConsentRepositoryFactory.provideConsentRepository(viewModelCImpl.provideConsentApiServiceProvider.get(), singletonCImpl.provideResponseHandlerProvider.get());

          case 5: // com.zenvest.feature_consent.data.network.ConsentApiService 
          return (T) ConsentModule_ProvideConsentApiServiceFactory.provideConsentApiService(singletonCImpl.provideRetrofitClientProvider.get());

          case 6: // com.zenvest.feature_dashboard.presentation.viewmodel.DashboardViewModel 
          return (T) new DashboardViewModel(viewModelCImpl.provideDashboardRepositoryProvider.get(), singletonCImpl.provideSessionManagerProvider.get());

          case 7: // com.zenvest.feature_dashboard.domain.DashboardRepository 
          return (T) DashboardModule_ProvideDashboardRepositoryFactory.provideDashboardRepository(viewModelCImpl.provideDashboardApiServiceProvider.get(), singletonCImpl.provideResponseHandlerProvider.get());

          case 8: // com.zenvest.feature_dashboard.data.network.DashboardApiService 
          return (T) DashboardModule_ProvideDashboardApiServiceFactory.provideDashboardApiService(singletonCImpl.provideRetrofitClientProvider.get());

          case 9: // com.zenvest.feature_dashboard.presentation.viewmodel.DebtViewModel 
          return (T) new DebtViewModel(viewModelCImpl.provideDashboardRepositoryProvider.get());

          case 10: // com.zenvest.feature_dashboard.presentation.viewmodel.EmergencyFundViewModel 
          return (T) new EmergencyFundViewModel(viewModelCImpl.provideDashboardRepositoryProvider.get());

          case 11: // com.zenvest.feature_dashboard.presentation.viewmodel.GoalsViewModel 
          return (T) new GoalsViewModel(viewModelCImpl.provideDashboardRepositoryProvider.get());

          case 12: // com.zenvest.feature_dashboard.presentation.viewmodel.InvestmentsViewModel 
          return (T) new InvestmentsViewModel(viewModelCImpl.provideDashboardRepositoryProvider.get());

          case 13: // com.zenvest.feature_onboarding.presentation.viewmodel.OnboardingViewModel 
          return (T) new OnboardingViewModel(viewModelCImpl.provideOnboardingRepositoryProvider.get(), singletonCImpl.provideSessionManagerProvider.get());

          case 14: // com.zenvest.feature_onboarding.domain.OnboardingRepository 
          return (T) OnboardingModule_ProvideOnboardingRepositoryFactory.provideOnboardingRepository(viewModelCImpl.provideOnboardingApiServiceProvider.get(), singletonCImpl.provideResponseHandlerProvider.get());

          case 15: // com.zenvest.feature_onboarding.data.network.OnboardingApiService 
          return (T) OnboardingModule_ProvideOnboardingApiServiceFactory.provideOnboardingApiService(singletonCImpl.provideRetrofitClientProvider.get());

          case 16: // com.zenvest.ui.viewmodel.SplashViewModel 
          return (T) new SplashViewModel(singletonCImpl.provideSessionManagerProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends ZenvestApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends ZenvestApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends ZenvestApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<NetworkConfig> provideNetworkConfigProvider;

    private Provider<SessionManager> provideSessionManagerProvider;

    private Provider<AuthInterceptor> provideAuthInterceptorProvider;

    private Provider<RetrofitClient> provideRetrofitClientProvider;

    private Provider<ResponseHandler> provideResponseHandlerProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideNetworkConfigProvider = DoubleCheck.provider(new SwitchingProvider<NetworkConfig>(singletonCImpl, 1));
      this.provideSessionManagerProvider = DoubleCheck.provider(new SwitchingProvider<SessionManager>(singletonCImpl, 3));
      this.provideAuthInterceptorProvider = DoubleCheck.provider(new SwitchingProvider<AuthInterceptor>(singletonCImpl, 2));
      this.provideRetrofitClientProvider = DoubleCheck.provider(new SwitchingProvider<RetrofitClient>(singletonCImpl, 0));
      this.provideResponseHandlerProvider = DoubleCheck.provider(new SwitchingProvider<ResponseHandler>(singletonCImpl, 4));
    }

    @Override
    public void injectZenvestApp(ZenvestApp arg0) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.zenvest.core.network.client.RetrofitClient 
          return (T) NetworkModule_ProvideRetrofitClientFactory.provideRetrofitClient(singletonCImpl.provideNetworkConfigProvider.get(), singletonCImpl.provideAuthInterceptorProvider.get());

          case 1: // com.zenvest.core.network.config.NetworkConfig 
          return (T) NetworkModule_ProvideNetworkConfigFactory.provideNetworkConfig();

          case 2: // com.zenvest.core.network.interceptors.AuthInterceptor 
          return (T) NetworkModule_ProvideAuthInterceptorFactory.provideAuthInterceptor(singletonCImpl.provideSessionManagerProvider.get());

          case 3: // com.zenvest.core.common.session.SessionManager 
          return (T) SessionModule_ProvideSessionManagerFactory.provideSessionManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 4: // com.zenvest.core.network.response.ResponseHandler 
          return (T) NetworkModule_ProvideResponseHandlerFactory.provideResponseHandler();

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
