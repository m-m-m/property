/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.booleans;

import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;

/**
 * Implementation of {@link WritableBooleanProperty}.
 *
 * @since 1.0.0
 */
public class BooleanProperty extends Property<Boolean> implements WritableBooleanProperty {

  private Boolean value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public BooleanProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public BooleanProperty(String name, PropertyMetadata<Boolean> metadata) {

    super(name, metadata);
  }

  @Override
  protected Boolean doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Boolean newValue) {

    this.value = newValue;
  }

  // @Override
  // public ValidatorBuilderBoolean<PropertyBuilder<BooleanProperty>> withValdidator() {
  //
  // return withValdidator(x -> new ValidatorBuilderBoolean<>(x));
  // }

}
