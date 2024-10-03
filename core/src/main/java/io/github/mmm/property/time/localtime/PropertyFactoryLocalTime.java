/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.localtime;

import java.time.LocalTime;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractSimplePropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * This is the implementation of {@link PropertyFactory} for {@link LocalTimeProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryLocalTime extends AbstractSimplePropertyFactory<LocalTime, LocalTimeProperty> {

  @Override
  public Class<LocalTime> getValueClass() {

    return LocalTime.class;
  }

  @Override
  public Class<? extends ReadableProperty<LocalTime>> getReadableInterface() {

    return ReadableLocalTimeProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<LocalTime>> getWritableInterface() {

    return WritableLocalTimeProperty.class;
  }

  @Override
  public Class<LocalTimeProperty> getImplementationClass() {

    return LocalTimeProperty.class;
  }

  @Override
  public LocalTimeProperty create(String name, PropertyMetadata<LocalTime> metadata) {

    return new LocalTimeProperty(name, metadata);
  }

}
