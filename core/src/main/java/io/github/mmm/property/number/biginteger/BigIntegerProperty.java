/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.biginteger;

import java.math.BigInteger;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.number.NumberProperty;

/**
 * Implementation of {@link WritableBigIntegerProperty}.
 *
 * @since 1.0.0
 */
public class BigIntegerProperty extends NumberProperty<BigInteger> implements WritableBigIntegerProperty {

  private BigInteger value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public BigIntegerProperty(String name) {

    this(name, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public BigIntegerProperty(String name, PropertyMetadata<BigInteger> metadata) {

    this(name, null, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public BigIntegerProperty(String name, BigInteger value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public BigIntegerProperty(String name, BigInteger value, PropertyMetadata<BigInteger> metadata) {

    super(name, metadata);
    this.value = value;
  }

  @Override
  protected BigInteger doGet() {

    return this.value;
  }

  @Override
  protected void doSet(BigInteger newValue) {

    this.value = newValue;
  }

}
