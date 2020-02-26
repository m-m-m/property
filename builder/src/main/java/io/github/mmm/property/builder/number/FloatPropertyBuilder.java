/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.number;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.number.floats.FloatProperty;
import io.github.mmm.validation.number.ValidatorBuilderFloat;

/**
 * {@link PropertyBuilder} for {@link FloatProperty}.
 *
 * @since 1.0.0
 */
public final class FloatPropertyBuilder extends
    ComparablePropertyBuilder<Float, FloatProperty, ValidatorBuilderFloat<FloatPropertyBuilder>, FloatPropertyBuilder> {

  /**
   * The constructor.
   */
  public FloatPropertyBuilder() {

    super();
  }

  @Override
  protected ValidatorBuilderFloat<FloatPropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderFloat<>(this);
  }

  @Override
  protected FloatProperty build(String name, PropertyMetadata<Float> metadata) {

    return new FloatProperty(name, metadata);
  }

}
