/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.number;

import java.math.BigDecimal;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilders;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.number.bigdecimal.BigDecimalProperty;
import io.github.mmm.validation.number.ValidatorBuilderBigDecimal;

/**
 * {@link PropertyBuilder} for {@link BigDecimalProperty}.
 *
 * @since 1.0.0
 */
public final class BigDecimalPropertyBuilder extends
    ComparablePropertyBuilder<BigDecimal, BigDecimalProperty, ValidatorBuilderBigDecimal<BigDecimalPropertyBuilder>, BigDecimalPropertyBuilder> {

  /**
   * The constructor.
   *
   * @param parent the {@link PropertyBuilders}.
   */
  public BigDecimalPropertyBuilder(PropertyBuilders parent) {

    super(parent);
  }

  @Override
  protected ValidatorBuilderBigDecimal<BigDecimalPropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderBigDecimal<>(this);
  }

  @Override
  protected BigDecimalProperty build(String name, PropertyMetadata<BigDecimal> metadata) {

    return new BigDecimalProperty(name, metadata);
  }

}
