/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.doubles;

import javax.inject.Named;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.property.api.factory.AbstractPropertyFactory;
import net.sf.mmm.property.api.factory.PropertyFactory;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * Implementation of {@link PropertyFactory} for {@link DoubleProperty}.
 *
 * @since 1.0.0
 */
@Named
public class PropertyFactoryDouble extends AbstractPropertyFactory<Double, DoubleProperty> {

  @Override
  public Class<Double> getValueClass() {

    return Double.class;
  }

  @Override
  public Class<? extends ReadableProperty<Double>> getReadableInterface() {

    return ReadableDoubleProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Double>> getWritableInterface() {

    return WritableDoubleProperty.class;
  }

  @Override
  public Class<DoubleProperty> getImplementationClass() {

    return DoubleProperty.class;
  }

  @Override
  public DoubleProperty create(String name, Class<? extends Double> valueType, Object bean,
      AbstractValidator<? super Double> validator) {

    return new DoubleProperty(name, bean, validator);
  }

}
