/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.number;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.number.shorts.ShortProperty;
import io.github.mmm.validation.number.ValidatorBuilderShort;

/**
 * {@link PropertyBuilder} for {@link ShortProperty}.
 */
public final class ShortPropertyBuilder
    extends PropertyBuilder<Short, ShortProperty, ValidatorBuilderShort<ShortPropertyBuilder>, ShortPropertyBuilder> {

  /**
   * The constructor.
   */
  public ShortPropertyBuilder() {

    super();
  }

  @Override
  protected ValidatorBuilderShort<ShortPropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderShort<>(this);
  }

  @Override
  protected ShortProperty build(String name, PropertyMetadata<Short> metadata) {

    return new ShortProperty(name, metadata);
  }

}