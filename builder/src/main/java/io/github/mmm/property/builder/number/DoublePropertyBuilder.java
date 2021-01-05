/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.number;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilders;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.number.doubles.DoubleProperty;
import io.github.mmm.validation.number.ValidatorBuilderDouble;

/**
 * {@link PropertyBuilder} for {@link DoubleProperty}.
 *
 * @since 1.0.0
 */
public final class DoublePropertyBuilder extends
    ComparablePropertyBuilder<Double, DoubleProperty, ValidatorBuilderDouble<DoublePropertyBuilder>, DoublePropertyBuilder> {

  /**
   * The constructor.
   *
   * @param parent the {@link PropertyBuilders}.
   */
  public DoublePropertyBuilder(PropertyBuilders parent) {

    super(parent);
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
