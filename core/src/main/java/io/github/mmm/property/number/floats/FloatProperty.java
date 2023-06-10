/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.floats;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.number.NumberProperty;

/**
 * Implementation of {@link WritableFloatProperty}.
 *
 * @since 1.0.0
 */
public class FloatProperty extends NumberProperty<Float> implements WritableFloatProperty {

  private Float value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public FloatProperty(String name) {

    this(name, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public FloatProperty(String name, PropertyMetadata<Float> metadata) {

    this(name, null, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public FloatProperty(String name, Float value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public FloatProperty(String name, Float value, PropertyMetadata<Float> metadata) {

    super(name, metadata);
    this.value = value;
  }

  @Override
  protected Float doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Float newValue) {

    this.value = newValue;
  }

}
