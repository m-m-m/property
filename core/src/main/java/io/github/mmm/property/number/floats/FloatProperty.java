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

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public FloatProperty(String name, PropertyMetadata<Float> metadata) {

    super(name, metadata);
  }

  @Override
  protected Float doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Float newValue) {

    this.value = newValue;
  }

  // @Override
  // public ValidatorBuilderFloat<PropertyBuilder<FloatProperty>> withValdidator() {
  //
  // return withValdidator(x -> new ValidatorBuilderFloat<>(x));
  // }

}
