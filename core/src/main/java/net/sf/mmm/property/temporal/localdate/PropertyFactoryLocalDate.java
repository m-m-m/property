/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.temporal.localdate;

import java.time.LocalDate;

import javax.inject.Named;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.property.factory.AbstractPropertyFactory;
import net.sf.mmm.property.factory.PropertyFactory;

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
  public LocalDateProperty create(String name, Class<? extends LocalDate> valueClass,
      PropertyMetadata<LocalDate> metadata) {

    return new LocalDateProperty(name, metadata);
  }

}
