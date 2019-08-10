/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.longs;

import javax.inject.Named;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.property.api.factory.AbstractPropertyFactory;
import net.sf.mmm.property.api.factory.PropertyFactory;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * This is the implementation of {@link PropertyFactory} for {@link LongProperty}.
 *
 * @author hohwille
 * @since 1.0.0
 */
@Named
public class PropertyFactoryLong extends AbstractPropertyFactory<Long, LongProperty> {

  @Override
  public Class<Long> getValueClass() {

    return Long.class;
  }

  @Override
  public Class<? extends ReadableProperty<Long>> getReadableInterface() {

    return ReadableLongProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Long>> getWritableInterface() {

    return WritableLongProperty.class;
  }

  @Override
  public Class<LongProperty> getImplementationClass() {

    return LongProperty.class;
  }

  @Override
  public LongProperty create(String name, Class<? extends Long> valueType, Object bean,
      AbstractValidator<? super Long> validator) {

    return new LongProperty(name, bean, validator);
  }

}
