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

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public IntegerProperty(String name, PropertyMetadata<Integer> metadata) {

    super(name, metadata);
  }

  @Override
  protected Integer doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Integer newValue) {

    this.value = newValue;
  }

  // @Override
  // public ValidatorBuilderInteger<PropertyBuilder<IntegerProperty>> withValdidator() {
  //
  // return withValdidator(x -> new ValidatorBuilderInteger<>(x));
  // }

}
