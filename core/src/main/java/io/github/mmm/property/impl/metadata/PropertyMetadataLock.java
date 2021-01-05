/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.impl.metadata;

import java.util.function.Supplier;

import io.github.mmm.property.AbstractPropertyMetadata;
import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.impl.readonly.AttributeNeverReadOnly;
import io.github.mmm.validation.Validator;

/**
 * Implementation of {@link PropertyMetadata} with {@link #getLock() lock}.
 *
 * @param <V> type of the {@link Property#get() property value}.
 *
 * @since 1.0.0
 */
public class PropertyMetadataLock<V> extends AbstractPropertyMetadata<V> {

  /** @see #getLock() */
  protected final AttributeReadOnly lock;

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   */
  public PropertyMetadataLock(AttributeReadOnly lock) {

    super();
    if (lock == null) {
      this.lock = AttributeNeverReadOnly.INSTANCE;
    } else {
      this.lock = lock;
    }
  }

  @Override
  public AttributeReadOnly getLock() {

    return this.lock;
  }

  @Override
  public PropertyMetadata<V> withLock(AttributeReadOnly newLock) {

    if (newLock != this.lock) {
      if ((newLock == null) || (newLock == AttributeNeverReadOnly.INSTANCE)) {
        return PropertyMetadataNone.get();
      }
      return new PropertyMetadataLock<>(newLock);
    }
    return this;
  }

  @Override
  public PropertyMetadata<V> withValidator(Validator<? super V> newValidator) {

    if (Validator.isValidating(newValidator)) {
      return new PropertyMetadataValidator<>(this.lock, newValidator);
    }
    return this;
  }

  @Override
  public PropertyMetadata<V> withExpression(Supplier<? extends V> newExpression) {

    if (newExpression != null) {
      return new PropertyMetadataExpression<>(this.lock, null, newExpression);
    }
    return this;
  }

}
