package com.zenvest.core.network.di;

import com.zenvest.core.network.response.ResponseHandler;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class NetworkModule_ProvideResponseHandlerFactory implements Factory<ResponseHandler> {
  @Override
  public ResponseHandler get() {
    return provideResponseHandler();
  }

  public static NetworkModule_ProvideResponseHandlerFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ResponseHandler provideResponseHandler() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideResponseHandler());
  }

  private static final class InstanceHolder {
    private static final NetworkModule_ProvideResponseHandlerFactory INSTANCE = new NetworkModule_ProvideResponseHandlerFactory();
  }
}
