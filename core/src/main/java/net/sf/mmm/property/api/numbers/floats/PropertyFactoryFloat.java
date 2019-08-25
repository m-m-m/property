/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.floats;

import javax.inject.Named;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.property.api.factory.AbstractPropertyFactory;
import net.sf.mmm.property.api.factory.PropertyFactory;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * Implementation of {@link PropertyFactory} for {@link FloatProperty}.
 *
 * @since 1.0.0
 */
@Named
public class PropertyFactoryFloat extends AbstractPropertyFactory<Float, FloatProperty> {

  @Override
  public Class<Float> getValueClass() {

    return Float.class;
  }

  @Override
  public Class<? extends ReadableProperty<Float>> getReadableInterface() {

    return ReadableFloatProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Float>> getWritableInterface() {

    return WritableFloatProperty.class;
  }

  @Override
  public Class<FloatProperty> getImplementationClass() {

    return FloatProperty.class;
  }

  @Override
  public FloatProperty create(String name, Class<? extends Float> valueType, Object bean,
      AbstractValidator<? super Float> validator) {

    return new FloatProperty(name, bean, validator);
  }

}
