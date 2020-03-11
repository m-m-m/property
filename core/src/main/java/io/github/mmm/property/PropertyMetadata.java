/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property;

import java.lang.reflect.Type;
import java.util.function.Supplier;

import io.github.mmm.marshall.Marshalling;
import io.github.mmm.validation.Validator;

/**
 * Metadata of a {@link Property}. Implementations of {@link PropertyMetadata} are supposed to be immutable. It is
 * discouraged and may have odd effects if the {@link PropertyMetadata} changes after it has been passed to the
 * constructor of a property.
 *
 * @param <V> type of the {@link Property#get() property value}.
 *
 * @since 1.0.0
 */
public interface PropertyMetadata<V> {

  /**
   * @return the {@link Validator} used to {@link ReadableProperty#validate() validate} the property.
   */
  Validator<? super V> getValidator();

  /**
   * @return the optional {@link Supplier} that calculates the {@link ReadableProperty#get() value} dynamically. May be
   *         {@code null} for a regular property. If it is not {@code null} the property will automatically be
   *         {@link ReadableProperty#isReadOnly() read-only}. You should not provide an explicit {@link #getValidator()
   *         validator} if you provide an expression.
   */
  Supplier<? extends V> getExpression();

  /**
   * @param key the {@link java.util.Map#containsKey(Object) key} of the requested metadata.
   * @return the value of the metadata for the given {@code key}. Will be {@code null} if no metadata exists for the
   *         given {@code key}.
   * @see #get(String, Class)
   */
  Object get(String key);

  /**
   * @param <T> type of the metadata.
   * @param key the {@link java.util.Map#containsKey(Object) key} of the requested metadata.
   * @param type {@link Class} reflecting the type of the requested metadata value.
   * @return the value of the metadata for the given {@code key}. Will be {@code null} if no metadata exists for the
   *         given {@code key}.
   */
  @SuppressWarnings("unchecked")
  default <T> T get(String key, Class<T> type) {

    Object value = get(key);
    return (T) value;
  }

  /**
   * <b>ATTENTION:</b> This is a convenient method for {@link #get(String, Class)} where {@link Class#getName()} is used
   * as key. Be aware that this can only work for final classes, {@link java.lang.annotation.Annotation}s or if the
   * producer of this {@link PropertyMetadata} provides the metadata under the {@code type} you are expecting as API.
   * Also it is discouraged to use this method for generic types such as {@link String} or {@link Long} as values of
   * such type can be anything and should have a semantic key.
   *
   * @param <T> type of the metadata.
   * @param type {@link Class} reflecting the type of the requested metadata value.
   * @return the value of the metadata for the given {@code key}. Will be {@code null} if no metadata exists for the
   *         given {@code key}.
   * @see #get(String, Class)
   */
  default <T> T get(Class<T> type) {

    return get(type.getName(), type);
  }

  /**
   * @return the {@link Iterable} of all {@link #get(String) metadata keys}.
   */
  Iterable<String> getKeys();

  /**
   * @return the optional {@link Type} of the {@link Property#get() property value}. May be {@code null}.
   * @see ReadableProperty#getValueClass()
   */
  Type getValueType();

  /**
   * @return the optional custom {@link Marshalling}. If {@code null} the default marshalling of the {@link Property} is
   *         used. Overriding also allows to extend or replace the property value with more complex data from this
   *         metadata - e.g. to reuse a {@code Bean} to represent a query to find instances of that {@code Bean}.
   */
  default Marshalling<V> getMarshalling() {

    return null;
  }

  /**
   * @return {@code true} if transient (e.g. computed and therefore not to be marshalled), {@code false} otherwise.
   * @see ReadableProperty#isTransient()
   */
  default boolean isTransient() {

    return (getExpression() != null);
  }

}
