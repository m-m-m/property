/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property;

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
  public PropertyMetadata<V> withValidator(Validator<? super V> validator) {

    if (Validator.isValidating(validator)) {
      return new PropertyMetadataType<>(validator);
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
