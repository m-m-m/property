/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.number.bytes;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.number.NumberProperty;

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
  protected Byte doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Byte newValue) {

    this.value = newValue;
  }

  // @Override
  // public ValidatorBuilderByte<PropertyBuilder<ByteProperty>> withValdidator() {
  //
  // return withValdidator(x -> new ValidatorBuilderByte<>(x));
  // }

}
