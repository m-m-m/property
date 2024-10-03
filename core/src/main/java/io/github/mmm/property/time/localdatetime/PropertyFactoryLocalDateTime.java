/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.localdatetime;

import java.time.LocalDateTime;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractSimplePropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * This is the implementation of {@link PropertyFactory} for {@link LocalDateTimeProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryLocalDateTime extends AbstractSimplePropertyFactory<LocalDateTime, LocalDateTimeProperty> {

  @Override
  public Class<LocalDateTime> getValueClass() {

    return LocalDateTime.class;
  }

  @Override
  public Class<? extends ReadableProperty<LocalDateTime>> getReadableInterface() {

    return ReadableLocalDateTimeProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<LocalDateTime>> getWritableInterface() {

    return WritableLocalDateTimeProperty.class;
  }

  @Override
  public Class<LocalDateTimeProperty> getImplementationClass() {

    return LocalDateTimeProperty.class;
  }

  @Override
  public LocalDateTimeProperty create(String name, PropertyMetadata<LocalDateTime> metadata) {

    return new LocalDateTimeProperty(name, metadata);
  }

}
