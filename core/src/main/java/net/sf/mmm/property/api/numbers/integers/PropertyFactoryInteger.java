/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.integers;

import javax.inject.Named;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.property.api.factory.AbstractPropertyFactory;
import net.sf.mmm.property.api.factory.PropertyFactory;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * Implementation of {@link PropertyFactory} for {@link IntegerProperty}.
 *
 * @since 1.0.0
 */
@Named
public class PropertyFactoryInteger extends AbstractPropertyFactory<Integer, IntegerProperty> {

  @Override
  public Class<Integer> getValueClass() {

    return Integer.class;
  }

  @Override
  public Class<? extends ReadableProperty<Integer>> getReadableInterface() {

    return ReadableIntegerProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Integer>> getWritableInterface() {

    return WritableIntegerProperty.class;
  }

  @Override
  public Class<IntegerProperty> getImplementationClass() {

    return IntegerProperty.class;
  }

  @Override
  public IntegerProperty create(String name, Class<? extends Integer> valueType, Object bean,
      AbstractValidator<? super Integer> validator) {

    return new IntegerProperty(name, bean, validator);
  }

}
