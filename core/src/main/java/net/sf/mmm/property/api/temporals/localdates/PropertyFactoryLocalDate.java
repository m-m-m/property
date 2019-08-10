/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.temporals.localdates;

import java.time.LocalDate;

import javax.inject.Named;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.property.api.factory.AbstractPropertyFactory;
import net.sf.mmm.property.api.factory.PropertyFactory;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * This is the implementation of {@link PropertyFactory} for {@link LocalDateProperty}.
 *
 * @author hohwille
 * @since 1.0.0
 */
@Named
public class PropertyFactoryLocalDate extends AbstractPropertyFactory<LocalDate, LocalDateProperty> {

  @Override
  public Class<LocalDate> getValueClass() {

    return LocalDate.class;
  }

  @Override
  public Class<? extends ReadableProperty<LocalDate>> getReadableInterface() {

    return ReadableLocalDateProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<LocalDate>> getWritableInterface() {

    return WritableLocalDateProperty.class;
  }

  @Override
  public Class<LocalDateProperty> getImplementationClass() {

    return LocalDateProperty.class;
  }

  @Override
  public LocalDateProperty create(String name, Class<? extends LocalDate> valueType, Object bean,
      AbstractValidator<? super LocalDate> validator) {

    return new LocalDateProperty(name, bean, validator);
  }

}
