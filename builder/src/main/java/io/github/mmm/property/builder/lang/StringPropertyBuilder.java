/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.lang;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.string.StringProperty;
import io.github.mmm.validation.string.ValidatorBuilderString;

/**
 * {@link PropertyBuilder} for {@link StringProperty}.
 */
public final class StringPropertyBuilder extends
    PropertyBuilder<String, StringProperty, ValidatorBuilderString<StringPropertyBuilder>, StringPropertyBuilder> {

  /**
   * The constructor.
   */
  public StringPropertyBuilder() {

    super();
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
