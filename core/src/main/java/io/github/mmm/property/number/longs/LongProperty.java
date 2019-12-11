/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.longs;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.number.NumberProperty;

/**
 * Implementation of {@link WritableLongProperty}.
 *
 * @since 1.0.0
 */
public class LongProperty extends NumberProperty<Long> implements WritableLongProperty {

  private Long value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public LongProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public LongProperty(String name, PropertyMetadata<Long> metadata) {

    super(name, metadata);
  }

  @Override
  protected Long doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Long newValue) {

    this.value = newValue;
  }

  // @Override
  // public ValidatorBuilderLong<PropertyBuilder<LongProperty>> withValdidator() {
  //
  // return withValdidator(x -> new ValidatorBuilderLong<>(x));
  // }

}
