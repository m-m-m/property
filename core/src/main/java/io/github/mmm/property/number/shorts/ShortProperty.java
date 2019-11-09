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

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public ShortProperty(String name, PropertyMetadata<Short> metadata) {

    super(name, metadata);
  }

  @Override
  protected Short doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Short newValue) {

    this.value = newValue;
  }

  // @Override
  // public ValidatorBuilderShort<PropertyBuilder<ShortProperty>> withValdidator() {
  //
  // return withValdidator(x -> new ValidatorBuilderShort<>(x));
  // }

}
