/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.bigdecimals;

import java.math.BigDecimal;

import javax.inject.Named;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.property.api.factory.AbstractPropertyFactory;
import net.sf.mmm.property.api.factory.PropertyFactory;
import net.sf.mmm.util.validation.base.AbstractValidator;

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
  public BigDecimalProperty create(String name, Class<? extends BigDecimal> valueClass, Object bean,
      AbstractValidator<? super BigDecimal> validator) {

    return new BigDecimalProperty(name, bean, validator);
  }

}
