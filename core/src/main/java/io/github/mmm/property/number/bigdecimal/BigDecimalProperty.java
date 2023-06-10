/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.bigdecimal;

import java.math.BigDecimal;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.number.NumberProperty;

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

    this(name, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public BigDecimalProperty(String name, PropertyMetadata<BigDecimal> metadata) {

    this(name, null, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public BigDecimalProperty(String name, BigDecimal value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public BigDecimalProperty(String name, BigDecimal value, PropertyMetadata<BigDecimal> metadata) {

    super(name, metadata);
    this.value = value;
  }

  @Override
  protected BigDecimal doGet() {

    return this.value;
  }

  @Override
  protected void doSet(BigDecimal newValue) {

    this.value = newValue;
  }

}
