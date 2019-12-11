/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.lang;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.booleans.BooleanProperty;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.validation.main.ValidatorBuilderBoolean;

/**
 * {@link PropertyBuilder} for {@link BooleanProperty}.
 */
public final class BooleanPropertyBuilder extends
    PropertyBuilder<Boolean, BooleanProperty, ValidatorBuilderBoolean<BooleanPropertyBuilder>, BooleanPropertyBuilder> {

  /**
   * The constructor.
   */
  public BooleanPropertyBuilder() {

    super();
  }

  @Override
  protected ValidatorBuilderBoolean<BooleanPropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderBoolean<>(this);
  }

  @Override
  protected BooleanProperty build(String name, PropertyMetadata<Boolean> metadata) {

    return new BooleanProperty(name, metadata);
  }

}
