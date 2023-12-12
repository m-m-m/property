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
 * Implementation of {@link PropertyMetadata} with {@link #getLock() lock}, {@link #getValidator() validator},
 * {@link #getExpression() expression}, and {@link #getMetaInfo() meta-info}.
 *
 * @param <V> type of the {@link Property#get() property value}.
 *
 * @since 1.0.0
 */
public class PropertyMetadataInfo<V> extends PropertyMetadataExpression<V> {

  /** @see #getMetaInfo() */
  protected final MetaInfo metaInfo;

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   * @param validator the {@link #getValidator() validator}.
   * @param expression the {@link #getExpression() expression}.
   * @param metaInfo the {@link #getMetaInfo() meta-information}.
   */
  public PropertyMetadataInfo(AttributeReadOnly lock, Validator<? super V> validator, Supplier<? extends V> expression,
      MetaInfo metaInfo) {

    super(lock, validator, expression);
    if (metaInfo == null) {
      this.metaInfo = MetaInfo.empty();
    } else {
      this.metaInfo = metaInfo;
    }
  }

  @Override
  public MetaInfo getMetaInfo() {

    return this.metaInfo;
  }

  @Override
  public PropertyMetadata<V> withLock(AttributeReadOnly newLock) {

    if (newLock != this.lock) {
      return new PropertyMetadataInfo<>(newLock, this.validator, this.expression, this.metaInfo);
    }
    return this;
  }

  @Override
  public PropertyMetadata<V> withValidator(Validator<? super V> newValidator) {

    if (newValidator != this.validator) {
      return new PropertyMetadataInfo<>(this.lock, newValidator, this.expression, this.metaInfo);
    }
    return this;
  }

  @Override
  public PropertyMetadata<V> withMetaInfo(MetaInfo newMetaInfo) {

    if (newMetaInfo != this.metaInfo) {
      return new PropertyMetadataInfo<>(this.lock, this.validator, this.expression, newMetaInfo);
    }
    return this;
  }

}
