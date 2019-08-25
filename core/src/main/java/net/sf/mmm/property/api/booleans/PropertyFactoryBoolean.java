/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.booleans;

import javax.inject.Named;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.property.api.factory.AbstractPropertyFactory;
import net.sf.mmm.property.api.factory.PropertyFactory;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * Implementation of {@link PropertyFactory} for {@link BooleanProperty}.
 *
 * @since 1.0.0
 */
@Named
public class PropertyFactoryBoolean extends AbstractPropertyFactory<Boolean, BooleanProperty> {

  @Override
  public Class<? extends Boolean> getValueClass() {

    return Boolean.class;
  }

  @Override
  public Class<? extends ReadableProperty<Boolean>> getReadableInterface() {

    return ReadableBooleanProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Boolean>> getWritableInterface() {

    return WritableBooleanProperty.class;
  }

  @Override
  public Class<BooleanProperty> getImplementationClass() {

    return BooleanProperty.class;
  }

  @Override
  public BooleanProperty create(String name, Class<? extends Boolean> valueType, Object bean,
      AbstractValidator<? super Boolean> validator) {

    return new BooleanProperty(name, bean, validator);
  }

}
