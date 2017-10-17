/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.impl.factory;

import javax.inject.Named;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.property.api.factory.PropertyFactory;
import net.sf.mmm.property.api.math.ByteProperty;
import net.sf.mmm.property.api.math.ReadableByteProperty;
import net.sf.mmm.property.api.math.WritableByteProperty;
import net.sf.mmm.util.reflect.api.GenericType;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * This is the implementation of {@link PropertyFactory} for {@link ByteProperty}.
 *
 * @author hohwille
 * @since 1.0.0
 */
@Named
public class PropertyFactoryByte extends AbstractPropertyFactory<Number, ByteProperty> {

  @Override
  public Class<Byte> getValueClass() {

    return Byte.class;
  }

  @Override
  public Class<? extends ReadableProperty<Number>> getReadableInterface() {

    return ReadableByteProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Number>> getWritableInterface() {

    return WritableByteProperty.class;
  }

  @Override
  public Class<ByteProperty> getImplementationClass() {

    return ByteProperty.class;
  }

  @Override
  public ByteProperty create(String name, GenericType<? extends Number> valueType, Object bean, AbstractValidator<? super Number> validator) {

    return new ByteProperty(name, bean, validator);
  }

}
