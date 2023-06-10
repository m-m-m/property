/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.shorts;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.number.NumberProperty;

/**
 * Implementation of {@link WritableShortProperty}.
 *
 * @since 1.0.0
 */
public class ShortProperty extends NumberProperty<Short> implements WritableShortProperty {

  private Short value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public ShortProperty(String name) {

    this(name, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public ShortProperty(String name, PropertyMetadata<Short> metadata) {

    this(name, null, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public ShortProperty(String name, Short value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public ShortProperty(String name, Short value, PropertyMetadata<Short> metadata) {

    super(name, metadata);
    this.value = value;
  }

  @Override
  protected Short doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Short newValue) {

    this.value = newValue;
  }

}
