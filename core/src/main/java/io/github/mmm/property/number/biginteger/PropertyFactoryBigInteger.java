/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.biginteger;

import java.math.BigInteger;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractSimplePropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link BigIntegerProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryBigInteger extends AbstractSimplePropertyFactory<BigInteger, BigIntegerProperty> {

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
  public BigIntegerProperty create(String name, PropertyMetadata<BigInteger> metadata) {

    return new BigIntegerProperty(name, metadata);
  }

}
