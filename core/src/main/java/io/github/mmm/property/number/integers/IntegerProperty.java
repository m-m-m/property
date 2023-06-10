/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.integers;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.number.NumberProperty;

/**
 * Implementation of {@link WritableIntegerProperty}.
 *
 * @since 1.0.0
 */
public class IntegerProperty extends NumberProperty<Integer> implements WritableIntegerProperty {

  private Integer value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public IntegerProperty(String name) {

    this(name, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public IntegerProperty(String name, PropertyMetadata<Integer> metadata) {

    this(name, null, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public IntegerProperty(String name, Integer value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public IntegerProperty(String name, Integer value, PropertyMetadata<Integer> metadata) {

    super(name, metadata);
    this.value = value;
  }

  @Override
  protected Integer doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Integer newValue) {

    this.value = newValue;
  }

}
