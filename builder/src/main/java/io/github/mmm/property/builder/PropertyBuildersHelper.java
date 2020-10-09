/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.PropertyMetadataFactory;
import io.github.mmm.property.PropertyMetadataNone;

/**
 * Internal helper class for {@link io.github.mmm.property.builder.DefaultPropertyBuilders}.
 *
 * @since 1.0.0
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public final class PropertyBuildersHelper {

  private PropertyBuildersHelper() {

  }

  /**
   * @param <T> type of result.
   * @param input the input value.
   * @param registry the potential {@link Function} to get the result from the given {@code input}.
   * @param factory the {@link Supplier} factory to create the result if not provided by {@code inputFunction}.
   * @return the result.
   */
  public static <T> T get(Object input, Object registry, Function<PropertyMetadata, T> factory) {

    T result = null;
    if (registry instanceof Function) {
      result = (T) ((Function) registry).apply(input);
    }
    PropertyMetadata<?> metadata;
    if (registry instanceof PropertyMetadataFactory) {
      metadata = ((PropertyMetadataFactory) registry).create(null, null, null, null);
    } else {
      metadata = PropertyMetadataNone.get();
    }
    if (result == null) {
      result = factory.apply(metadata);
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
    if (registry instanceof PropertyMetadataFactory) {
      builder.metadataFactory((PropertyMetadataFactory) registry);
    }
    return builder;
  }

}
