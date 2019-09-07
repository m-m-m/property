/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.number.biginteger;

import java.math.BigInteger;

import javax.inject.Named;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.property.factory.AbstractPropertyFactory;
import net.sf.mmm.property.factory.PropertyFactory;

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
  public BigIntegerProperty create(String name, Class<? extends BigInteger> valueClass,
      PropertyMetadata<BigInteger> metadata) {

    return new BigIntegerProperty(name, metadata);
  }

}
