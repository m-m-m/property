/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.lang;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.booleans.BooleanProperty;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.validation.main.ValidatorBuilderBoolean;

/**
 * {@link PropertyBuilder} for {@link BooleanProperty}.
 *
 * @since 1.0.0
 */
public final class BooleanPropertyBuilder extends
    PropertyBuilder<Boolean, BooleanProperty, ValidatorBuilderBoolean<BooleanPropertyBuilder>, BooleanPropertyBuilder> {

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   */
  public BooleanPropertyBuilder(AttributeReadOnly lock) {

    super(lock);
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
