/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.impl;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import io.github.mmm.property.builder.PropertyBuilder;

/**
 * Internal helper class for e.g. {@link io.github.mmm.property.builder.DefaultPropertyBuilders}.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public final class RegistryHelper {

  private RegistryHelper() {

  }

  /**
   * @param <T> type of result.
   * @param input the input value.
   * @param inputFunction the potential {@link Function} to get the result from the given {@code input}.
   * @param factory the {@link Supplier} factory to create the result if not provided by {@code inputFunction}.
   * @return the result.
   */
  public static <T> T get(Object input, Object inputFunction, Supplier<T> factory) {

    T result = null;
    if (inputFunction instanceof Function) {

      result = (T) ((Function) inputFunction).apply(input);
    }
    if (result == null) {
      result = factory.get();
    }
    return result;
  }

  /**
   * @param <T> type of {@code object}.
   * @param object the object to {@link Consumer#accept(Object) accept}.
   * @param consumer the object that may implement {@link Consumer} to {@link Consumer#accept(Object) accept} the given
   *        {@code object}.
   * @return the given {@code object}.
   */
  public static <T> T accept(T object, Object consumer) {

    if (consumer instanceof Consumer) {

      ((Consumer) consumer).accept(object);
    }
    return object;
  }

  /**
   * @param <B> type of the {@link PropertyBuilder}.
   * @param builder the {@link PropertyBuilder} to configure.
   * @param registry the {@link io.github.mmm.property.builder.DefaultPropertyBuilders builder factory}.
   * @return the given {@link PropertyBuilder}.
   */
  public static <B extends PropertyBuilder<?, ?, ?, ?>> B builder(B builder, Object registry) {

    if (registry instanceof Consumer) {
      builder.registry((Consumer) registry);
    }
    if (registry instanceof Function) {
      builder.factory((Function) registry);
    }
    return builder;
  }

}