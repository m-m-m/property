/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.Supplier;

import io.github.mmm.marshall.Marshalling;
import io.github.mmm.property.impl.metadata.PropertyMetadataExpression;
import io.github.mmm.property.impl.metadata.PropertyMetadataLock;
import io.github.mmm.property.impl.metadata.PropertyMetadataMap;
import io.github.mmm.property.impl.metadata.PropertyMetadataNone;
import io.github.mmm.property.impl.metadata.PropertyMetadataValidator;
import io.github.mmm.property.impl.metadata.PropertyMetadataValueType;
import io.github.mmm.property.impl.readonly.AttributeNeverReadOnly;
import io.github.mmm.validation.Validator;

/**
 * Metadata of a {@link Property}. Implementations of {@link PropertyMetadata} are supposed to be immutable. It is
 * discouraged and may have odd effects if the {@link PropertyMetadata} changes after it has been passed to the
 * constructor of a property.
 *
 * @param <V> type of the {@link Property#get() property value}.
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
   * @return the {@link Map#size() size} of the {@link #getKeys() key}/ {@link #get(Class) value} pairs in this map.
   */
  int getKeyCount();

  /**
   * @return {@code true} if this metadata has at least one {@link #getKeys() key}/{@link #get(String) value} pair,
   *         {@code false} otherwise (if empty and {@link #getKeyCount() key count} is {@code 0}).
   */
  default boolean hasKeys() {

    return getKeyCount() > 0;
  }

  /**
   * @return a new or immutable {@link Map} with this metadata {@link #getKeys() key}/{@link #get(Class) value} pairs.
   */
  Map<String, Object> asMap();

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

  /**
   * @param newLock the new {@link #getLock() lock}.
   * @return a new instance of {@link PropertyMetadata} with the given {@link AttributeReadOnly} used for
   *         {@link #getLock()}.
   */
  PropertyMetadata<V> withLock(AttributeReadOnly newLock);

  /**
   * @param newValidator the new {@link Validator}.
   * @return a new instance of {@link PropertyMetadata} with the given {@link Validator} used for
   *         {@link #getValidator()}.
   */
  PropertyMetadata<V> withValidator(Validator<? super V> newValidator);

  /**
   * @param newExpression the new {@link #getExpression() expression}.
   * @return a new instance of {@link PropertyMetadata} with the given {@link Supplier} used for
   *         {@link #getExpression()}.
   */
  PropertyMetadata<V> withExpression(Supplier<? extends V> newExpression);

  /**
   * @return the lock object that can make the owning {@link Property} {@link AttributeReadOnly#isReadOnly() read-only}.
   *         Typically this is the owning bean.
   */
  default AttributeReadOnly getLock() {

    return AttributeNeverReadOnly.INSTANCE;
  }

  /**
   * @param <V> type of the {@link Property#get() property value}.
   * @param lock the {@link PropertyMetadata#getLock() lock} (owning bean).
   * @param validator the {@link PropertyMetadata#getValidator() validator}.
   * @param expression the {@link PropertyMetadata#getExpression() expression}.
   * @param valueType the {@link PropertyMetadata#getValueType() value type}.
   * @param map the {@link PropertyMetadata#get(String) metadata} {@link Map}.
   * @return the new {@link PropertyMetadata}.
   */
  static <V> PropertyMetadata<V> of(AttributeReadOnly lock, Validator<? super V> validator,
      Supplier<? extends V> expression, Type valueType, Map<String, Object> map) {

    if (isNotEmpty(map)) {
      return new PropertyMetadataMap<>(lock, validator, expression, valueType, map);
    } else if (valueType != null) {
      return new PropertyMetadataValueType<>(lock, validator, expression, valueType);
    } else if (expression != null) {
      return new PropertyMetadataExpression<>(lock, validator, expression);
    } else if (Validator.isValidating(validator)) {
      return new PropertyMetadataValidator<>(lock, validator);
    } else if (lock != null) {
      return new PropertyMetadataLock<>(lock);
    }
    return PropertyMetadataNone.get();
  }

  private static boolean isNotEmpty(Map<?, ?> map) {

    if (map == null) {
      return false;
    }
    return !map.isEmpty();
  }

  /**
   * @param <V> type of the {@link Property#get() property value}.
   * @param lock the {@link PropertyMetadata#getLock() lock} (owning bean).
   * @return the new {@link PropertyMetadata}.
   */
  static <V> PropertyMetadata<V> of(AttributeReadOnly lock) {

    return of(lock, null, null, null, null);
  }

  /**
   * @param <V> type of the {@link Property#get() property value}.
   * @param lock the {@link PropertyMetadata#getLock() lock} (owning bean).
   * @param validator the {@link PropertyMetadata#getValidator() validator}.
   * @return the new {@link PropertyMetadata}.
   */
  static <V> PropertyMetadata<V> of(AttributeReadOnly lock, Validator<? super V> validator) {

    return of(lock, validator, null, null, null);
  }

  /**
   * @param <V> type of the {@link Property#get() property value}.
   * @param lock the {@link PropertyMetadata#getLock() lock} (owning bean).
   * @param validator the {@link PropertyMetadata#getValidator() validator}.
   * @param expression the {@link PropertyMetadata#getExpression() expression}.
   * @return the new {@link PropertyMetadata}.
   */
  static <V> PropertyMetadata<V> of(AttributeReadOnly lock, Validator<? super V> validator,
      Supplier<? extends V> expression) {

    return of(lock, validator, expression, null, null);
  }

  /**
   * @param <V> type of the {@link Property#get() property value}.
   * @param lock the {@link PropertyMetadata#getLock() lock} (owning bean).
   * @param validator the {@link PropertyMetadata#getValidator() validator}.
   * @param expression the {@link PropertyMetadata#getExpression() expression}.
   * @param valueType the {@link PropertyMetadata#getValueType() value type}.
   * @return the new {@link PropertyMetadata}.
   */
  static <V> PropertyMetadata<V> of(AttributeReadOnly lock, Validator<? super V> validator,
      Supplier<? extends V> expression, Type valueType) {

    return of(lock, validator, expression, valueType, null);
  }

  /**
   * @param <V> type of the {@link Property#get() property value}.
   * @param lock the {@link PropertyMetadata#getLock() lock} (owning bean).
   * @param validator the {@link PropertyMetadata#getValidator() validator}.
   * @param expression the {@link PropertyMetadata#getExpression() expression}.
   * @return the new {@link PropertyMetadata}.
   */
  static <V> PropertyMetadata<V> ofExpression(Supplier<? extends V> expression) {

    return of(null, null, expression, null, null);
  }

}
