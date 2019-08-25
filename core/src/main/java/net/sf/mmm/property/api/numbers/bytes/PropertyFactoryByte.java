/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.bytes;

import javax.inject.Named;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.property.api.factory.AbstractPropertyFactory;
import net.sf.mmm.property.api.factory.PropertyFactory;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * Implementation of {@link PropertyFactory} for {@link ByteProperty}.
 *
 * @since 1.0.0
 */
@Named
public class PropertyFactoryByte extends AbstractPropertyFactory<Byte, ByteProperty> {

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
  public ByteProperty create(String name, Class<? extends Byte> valueType, Object bean,
      AbstractValidator<? super Byte> validator) {

    return new ByteProperty(name, bean, validator);
  }

}
