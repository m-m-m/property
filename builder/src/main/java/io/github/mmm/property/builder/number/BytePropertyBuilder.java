/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.number;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.number.bytes.ByteProperty;
import io.github.mmm.validation.number.ValidatorBuilderByte;

/**
 * {@link PropertyBuilder} for {@link ByteProperty}.
 *
 * @since 1.0.0
 */
public final class BytePropertyBuilder extends
    ComparablePropertyBuilder<Byte, ByteProperty, ValidatorBuilderByte<BytePropertyBuilder>, BytePropertyBuilder> {

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   */
  public BytePropertyBuilder(AttributeReadOnly lock) {

    super(lock);
  }

  @Override
  protected ValidatorBuilderByte<BytePropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderByte<>(this);
  }

  @Override
  protected ByteProperty build(String name, PropertyMetadata<Byte> metadata) {

    return new ByteProperty(name, metadata);
  }

}
