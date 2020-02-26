/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.number;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.number.integers.IntegerProperty;
import io.github.mmm.validation.number.ValidatorBuilderInteger;

/**
 * {@link PropertyBuilder} for {@link IntegerProperty}.
 *
 * @since 1.0.0
 */
public final class IntegerPropertyBuilder extends
    ComparablePropertyBuilder<Integer, IntegerProperty, ValidatorBuilderInteger<IntegerPropertyBuilder>, IntegerPropertyBuilder> {

  /**
   * The constructor.
   */
  public IntegerPropertyBuilder() {

    super();
  }

  @Override
  protected ValidatorBuilderInteger<IntegerPropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderInteger<>(this);
  }

  @Override
  protected IntegerProperty build(String name, PropertyMetadata<Integer> metadata) {

    return new IntegerProperty(name, metadata);
  }

}
