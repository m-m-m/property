/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.impl.metadata;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;
import java.util.function.Supplier;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.validation.Validator;

/**
 * Implementation of {@link PropertyMetadata} with {@link #getLock() lock}, {@link #getValidator() validator},
 * {@link #getExpression() expression}, and {@link #getValueType() value type}.
 *
 * @param <V> type of the {@link Property#get() property value}.
 *
 * @since 1.0.0
 */
public class PropertyMetadataMap<V> extends PropertyMetadataValueType<V> {

  /** @see #getValueType() */
  protected final Map<String, Object> map;

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   * @param validator the {@link #getValidator() validator}.
   * @param expression the {@link #getExpression() expression}.
   * @param valueType the {@link #getValueType() value type}.
   * @param map the {@link #asMap() metadata map}.
   */
  public PropertyMetadataMap(AttributeReadOnly lock, Validator<? super V> validator, Supplier<? extends V> expression,
      Type valueType, Map<String, Object> map) {

    super(lock, validator, expression, valueType);
    if (map == null) {
      this.map = Collections.emptyMap();
    } else {
      this.map = Collections.unmodifiableMap(map);
    }
  }

  @Override
  public Map<String, Object> asMap() {

    return this.map;
  }

  @Override
  public PropertyMetadata<V> withLock(AttributeReadOnly newLock) {

    if (newLock != this.lock) {
      return new PropertyMetadataMap<>(newLock, this.validator, this.expression, this.valueType, this.map);
    }
    return this;
  }

  @Override
  public PropertyMetadata<V> withValidator(Validator<? super V> newValidator) {

    if (newValidator != this.validator) {
      return new PropertyMetadataMap<>(this.lock, newValidator, this.expression, this.valueType, this.map);
    }
    return this;
  }

}
