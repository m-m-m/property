/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.impl.metadata;

import java.util.function.Supplier;

import io.github.mmm.base.metainfo.MetaInfo;
import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.validation.Validator;

/**
 * Implementation of {@link PropertyMetadata} with {@link #getLock() lock} and {@link #getValidator() validator}.
 *
 * @param <V> type of the {@link Property#get() property value}.
 *
 * @since 1.0.0
 */
public class PropertyMetadataValidator<V> extends PropertyMetadataLock<V> {

  /** @see #getValidator() */
  protected final Validator<? super V> validator;

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   * @param validator the {@link #getValidator() validator}.
   */
  public PropertyMetadataValidator(AttributeReadOnly lock, Validator<? super V> validator) {

    super(lock);
    if (validator == null) {
      this.validator = Validator.none();
    } else {
      this.validator = validator;
    }
  }

  @Override
  public Validator<? super V> getValidator() {

    return this.validator;
  }

  @Override
  public PropertyMetadata<V> withLock(AttributeReadOnly newLock) {

    if (newLock != this.lock) {
      return new PropertyMetadataValidator<>(newLock, this.validator);
    }
    return this;
  }

  @Override
  public PropertyMetadata<V> withValidator(Validator<? super V> newValidator) {

    if (newValidator != this.validator) {
      if (Validator.isValidating(newValidator)) {
        return new PropertyMetadataValidator<>(this.lock, newValidator);
      } else {
        return new PropertyMetadataLock<>(this.lock);
      }
    }
    return this;
  }

  @Override
  public PropertyMetadata<V> withExpression(Supplier<? extends V> newExpression) {

    if (newExpression != null) {
      return new PropertyMetadataExpression<>(this.lock, this.validator, newExpression);
    }
    return this;
  }

  @Override
  public PropertyMetadata<V> withMetaInfo(MetaInfo metaInfo) {

    if ((metaInfo != null) && metaInfo.isEmpty()) {
      return new PropertyMetadataInfo<>(this.lock, this.validator, null, metaInfo);
    }
    return this;
  }

}
