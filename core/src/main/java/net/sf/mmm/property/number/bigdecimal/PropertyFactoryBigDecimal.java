/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.number.bigdecimal;

import java.math.BigDecimal;

import javax.inject.Named;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.property.factory.AbstractPropertyFactory;
import net.sf.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link BigDecimalProperty}.
 *
 * @since 1.0.0
 */
@Named
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
