/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.impl.metadata;

import java.util.function.Supplier;

import io.github.mmm.base.metainfo.MetaInfo;
import io.github.mmm.property.AbstractPropertyMetadata;
import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.impl.readonly.AttributeNeverReadOnly;
import io.github.mmm.validation.Validator;

/**
 * Implementation of {@link PropertyMetadata} that is entirely empty.
 *
 * @param <V> type of the {@link Property#get() property value}.
 *
 * @since 1.0.0
 */
public final class PropertyMetadataNone<V> extends AbstractPropertyMetadata<V> {

  private static final PropertyMetadataNone<Object> INSTANCE = new PropertyMetadataNone<>();

  private PropertyMetadataNone() {

    super();
  }

  @Override
  public PropertyMetadata<V> withLock(AttributeReadOnly newLock) {

    if ((newLock != null) && (newLock != AttributeNeverReadOnly.INSTANCE)) {
      return new PropertyMetadataLock<>(newLock);
    }
    return this;
  }

  @Override
  public PropertyMetadata<V> withValidator(Validator<? super V> newValidator) {

    if (Validator.isValidating(newValidator)) {
      return new PropertyMetadataValidator<>(null, newValidator);
    }
    return this;
  }

  @Override
  public PropertyMetadata<V> withExpression(Supplier<? extends V> newExpression) {

    if (newExpression != null) {
      return new PropertyMetadataExpression<>(null, null, newExpression);
    }
    return this;
  }

  @Override
  public PropertyMetadata<V> withMetaInfo(MetaInfo metaInfo) {

    if ((metaInfo != null) && !metaInfo.isEmpty()) {
      return new PropertyMetadataInfo<>(null, null, null, metaInfo);
    }
    return this;
  }

  /**
   * @param <V> type of the {@link Property#get() property value}.
   * @return the singleton instance of this class.
   */
  @SuppressWarnings("unchecked")
  public static <V> PropertyMetadataNone<V> get() {

    return (PropertyMetadataNone<V>) INSTANCE;
  }

}
