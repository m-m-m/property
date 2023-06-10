/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.doubles;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.number.NumberProperty;

/**
 * Implementation of {@link WritableDoubleProperty}.
 *
 * @since 1.0.0
 */
public class DoubleProperty extends NumberProperty<Double> implements WritableDoubleProperty {

  private Double value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public DoubleProperty(String name) {

    this(name, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public DoubleProperty(String name, PropertyMetadata<Double> metadata) {

    this(name, null, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public DoubleProperty(String name, Double value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public DoubleProperty(String name, Double value, PropertyMetadata<Double> metadata) {

    super(name, metadata);
    this.value = value;
  }

  @Override
  protected Double doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Double newValue) {

    this.value = newValue;
  }

}
