/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.impl.metadata;

import java.lang.reflect.Type;
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
public class PropertyMetadataValueType<V> extends PropertyMetadataExpression<V> {

  /** @see #getValueType() */
  protected final Type valueType;

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   * @param validator the {@link #getValidator() validator}.
   * @param expression the {@link #getExpression() expression}.
   * @param valueType the {@link #getValueType() value type}.
   */
  public PropertyMetadataValueType(AttributeReadOnly lock, Validator<? super V> validator,
      Supplier<? extends V> expression, Type valueType) {

    super(lock, validator, expression);
    this.valueType = valueType;
  }

  @Override
  public Type getValueType() {

    return super.getValueType();
  }

  @Override
  public PropertyMetadata<V> withLock(AttributeReadOnly newLock) {

    if (newLock != this.lock) {
      return new PropertyMetadataValueType<>(newLock, this.validator, this.expression, this.valueType);
    }
    return this;
  }

  @Override
  public PropertyMetadata<V> withValidator(Validator<? super V> newValidator) {

    if (newValidator != this.validator) {
      return new PropertyMetadataValueType<>(this.lock, newValidator, this.expression, this.valueType);
    }
    return this;
  }

}
