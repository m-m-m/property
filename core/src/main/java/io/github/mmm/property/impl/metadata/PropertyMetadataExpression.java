/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.impl.metadata;

import java.util.function.Supplier;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.validation.Validator;

/**
 * Implementation of {@link PropertyMetadata} with {@link #getLock() lock}, {@link #getValidator() validator}, and
 * {@link #getExpression() expression}.
 *
 * @param <V> type of the {@link Property#get() property value}.
 *
 * @since 1.0.0
 */
public class PropertyMetadataExpression<V> extends PropertyMetadataValidator<V> {

  /** @see #getValidator() */
  protected final Supplier<? extends V> expression;

  /**
   * The constructor.
   *
   * @param expression the {@link #getExpression() expression}.
   */
  public PropertyMetadataExpression(Supplier<? extends V> expression) {

    this(null, null, expression);
  }

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   * @param validator the {@link #getValidator() validator}.
   * @param expression the {@link #getExpression() expression}.
   */
  public PropertyMetadataExpression(AttributeReadOnly lock, Validator<? super V> validator,
      Supplier<? extends V> expression) {

    super(lock, validator);
    this.expression = expression;
  }

  @Override
  public Supplier<? extends V> getExpression() {

    return this.expression;
  }

  @Override
  public PropertyMetadata<V> withLock(AttributeReadOnly newLock) {

    if (newLock != this.lock) {
      return new PropertyMetadataExpression<>(newLock, this.validator, this.expression);
    }
    return this;
  }

  @Override
  public PropertyMetadata<V> withValidator(Validator<? super V> newValidator) {

    if (newValidator != this.validator) {
      return new PropertyMetadataExpression<>(this.lock, newValidator, this.expression);
    }
    return this;
  }

}
