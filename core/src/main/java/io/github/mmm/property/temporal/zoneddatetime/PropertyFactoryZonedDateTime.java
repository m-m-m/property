/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.temporal.zoneddatetime;

import java.time.ZonedDateTime;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractPropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * This is the implementation of {@link PropertyFactory} for {@link ZonedDateTimeProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryZonedDateTime extends AbstractPropertyFactory<ZonedDateTime, ZonedDateTimeProperty> {

  @Override
  public Class<ZonedDateTime> getValueClass() {

    return ZonedDateTime.class;
  }

  @Override
  public Class<? extends ReadableProperty<ZonedDateTime>> getReadableInterface() {

    return ReadableZonedDateTimeProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<ZonedDateTime>> getWritableInterface() {

    return WritableZonedDateTimeProperty.class;
  }

  @Override
  public Class<ZonedDateTimeProperty> getImplementationClass() {

    return ZonedDateTimeProperty.class;
  }

  @Override
  public ZonedDateTimeProperty create(String name, Class<? extends ZonedDateTime> valueClass,
      PropertyMetadata<ZonedDateTime> metadata, WritableProperty<?> valueProperty) {

    return new ZonedDateTimeProperty(name, metadata);
  }

}
