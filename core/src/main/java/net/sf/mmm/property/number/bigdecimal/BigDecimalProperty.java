/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.number.bigdecimal;

import java.math.BigDecimal;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.number.NumberProperty;

/**
 * Implementation of {@link WritableBigDecimalProperty}.
 *
 * @since 1.0.0
 */
public class BigDecimalProperty extends NumberProperty<BigDecimal> implements WritableBigDecimalProperty {

  private BigDecimal value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public BigDecimalProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public BigDecimalProperty(String name, PropertyMetadata<BigDecimal> metadata) {

    super(name, metadata);
  }

  @Override
  protected BigDecimal doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(BigDecimal newValue) {

    this.value = newValue;
  }

  // @Override
  // public ValidatorBuilderBigDecimal<PropertyBuilder<BigDecimalProperty>> withValdidator() {
  //
  // return withValdidator(x -> new ValidatorBuilderBigDecimal<>(x));
  // }

}
