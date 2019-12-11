/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.number;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.number.doubles.DoubleProperty;
import io.github.mmm.validation.number.ValidatorBuilderDouble;

/**
 * {@link PropertyBuilder} for {@link DoubleProperty}.
 */
public final class DoublePropertyBuilder extends
    PropertyBuilder<Double, DoubleProperty, ValidatorBuilderDouble<DoublePropertyBuilder>, DoublePropertyBuilder> {

  /**
   * The constructor.
   */
  public DoublePropertyBuilder() {

    super();
  }

  @Override
  protected ValidatorBuilderDouble<DoublePropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderDouble<>(this);
  }

  @Override
  protected DoubleProperty build(String name, PropertyMetadata<Double> metadata) {

    return new DoubleProperty(name, metadata);
  }

}
