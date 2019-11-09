/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.temporal.localdate;

import java.time.LocalDate;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractPropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * This is the implementation of {@link PropertyFactory} for {@link LocalDateProperty}.
 *
 * @since 1.0.0
 */
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
