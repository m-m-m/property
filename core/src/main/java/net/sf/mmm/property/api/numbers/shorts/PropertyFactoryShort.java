/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.shorts;

import javax.inject.Named;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.property.api.factory.AbstractPropertyFactory;
import net.sf.mmm.property.api.factory.PropertyFactory;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * Implementation of {@link PropertyFactory} for {@link ShortProperty}.
 *
 * @since 1.0.0
 */
@Named
public class PropertyFactoryShort extends AbstractPropertyFactory<Short, ShortProperty> {

  @Override
  public Class<Short> getValueClass() {

    return Short.class;
  }

  @Override
  public Class<? extends ReadableProperty<Short>> getReadableInterface() {

    return ReadableShortProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Short>> getWritableInterface() {

    return WritableShortProperty.class;
  }

  @Override
  public Class<ShortProperty> getImplementationClass() {

    return ShortProperty.class;
  }

  @Override
  public ShortProperty create(String name, Class<? extends Short> valueType, Object bean,
      AbstractValidator<? super Short> validator) {

    return new ShortProperty(name, bean, validator);
  }

}
