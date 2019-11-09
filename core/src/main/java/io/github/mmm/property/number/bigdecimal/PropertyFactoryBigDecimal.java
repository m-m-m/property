/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.bigdecimal;

import java.math.BigDecimal;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractPropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link BigDecimalProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryBigDecimal extends AbstractPropertyFactory<BigDecimal, BigDecimalProperty> {

  @Override
  public Class<BigDecimal> getValueClass() {

    return BigDecimal.class;
  }

  @Override
  public Class<? extends ReadableProperty<BigDecimal>> getReadableInterface() {

    return ReadableBigDecimalProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<BigDecimal>> getWritableInterface() {

    return WritableBigDecimalProperty.class;
  }

  @Override
  public Class<BigDecimalProperty> getImplementationClass() {

    return BigDecimalProperty.class;
  }

  @Override
  public BigDecimalProperty create(String name, Class<? extends BigDecimal> valueClass,
      PropertyMetadata<BigDecimal> metadata) {

    return new BigDecimalProperty(name, metadata);
  }

}
