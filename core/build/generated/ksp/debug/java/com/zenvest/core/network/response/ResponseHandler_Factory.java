package com.zenvest.core.network.response;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class ResponseHandler_Factory implements Factory<ResponseHandler> {
  @Override
  public ResponseHandler get() {
    return newInstance();
  }

  public static ResponseHandler_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ResponseHandler newInstance() {
    return new ResponseHandler();
  }

  private static final class InstanceHolder {
    private static final ResponseHandler_Factory INSTANCE = new ResponseHandler_Factory();
  }
}
