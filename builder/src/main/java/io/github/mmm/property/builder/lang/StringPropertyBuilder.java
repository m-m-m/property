/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.lang;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.string.StringProperty;
import io.github.mmm.validation.string.ValidatorBuilderString;

/**
 * {@link PropertyBuilder} for {@link StringProperty}.
 *
 * @since 1.0.0
 */
public final class StringPropertyBuilder extends
    ComparablePropertyBuilder<String, StringProperty, ValidatorBuilderString<StringPropertyBuilder>, StringPropertyBuilder> {

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   */
  public StringPropertyBuilder(AttributeReadOnly lock) {

    super(lock);
  }

  @Override
  protected ValidatorBuilderString<StringPropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderString<>(this);
  }

  @Override
  protected StringProperty build(String name, PropertyMetadata<String> metadata) {

    return new StringProperty(name, metadata);
  }

}
