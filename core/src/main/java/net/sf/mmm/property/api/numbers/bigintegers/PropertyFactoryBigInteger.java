/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.bigintegers;

import java.math.BigInteger;

import javax.inject.Named;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.property.api.factory.AbstractPropertyFactory;
import net.sf.mmm.property.api.factory.PropertyFactory;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * Implementation of {@link PropertyFactory} for {@link BigIntegerProperty}.
 *
 * @since 1.0.0
 */
@Named
public class PropertyFactoryBigInteger extends AbstractPropertyFactory<BigInteger, BigIntegerProperty> {

  @Override
  public Class<BigInteger> getValueClass() {

    return BigInteger.class;
  }

  @Override
  public Class<? extends ReadableProperty<BigInteger>> getReadableInterface() {

    return ReadableBigIntegerProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<BigInteger>> getWritableInterface() {

    return WritableBigIntegerProperty.class;
  }

  @Override
  public Class<BigIntegerProperty> getImplementationClass() {

    return BigIntegerProperty.class;
  }

  @Override
  public BigIntegerProperty create(String name, Class<? extends BigInteger> valueClass, Object bean,
      AbstractValidator<? super BigInteger> validator) {

    return new BigIntegerProperty(name, bean, validator);
  }

}
