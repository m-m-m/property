/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.bytes;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractSimplePropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link ByteProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryByte extends AbstractSimplePropertyFactory<Byte, ByteProperty> {

  @Override
  public Class<Byte> getValueClass() {

    return Byte.class;
  }

  @Override
  public Class<? extends ReadableProperty<Byte>> getReadableInterface() {

    return ReadableByteProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Byte>> getWritableInterface() {

    return WritableByteProperty.class;
  }

  @Override
  public Class<ByteProperty> getImplementationClass() {

    return ByteProperty.class;
  }

  @Override
  public ByteProperty create(String name, PropertyMetadata<Byte> metadata) {

    return new ByteProperty(name, metadata);
  }

}
