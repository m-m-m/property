/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.bytes;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.number.NumberProperty;

/**
 * Implementation of {@link WritableByteProperty}.
 *
 * @since 1.0.0
 */
public class ByteProperty extends NumberProperty<Byte> implements WritableByteProperty {

  private Byte value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public ByteProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public ByteProperty(String name, PropertyMetadata<Byte> metadata) {

    super(name, metadata);
  }

  @Override
  protected Byte doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Byte newValue) {

    this.value = newValue;
  }

}
