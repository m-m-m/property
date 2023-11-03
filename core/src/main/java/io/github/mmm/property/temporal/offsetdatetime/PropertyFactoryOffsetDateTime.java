/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.temporal.offsetdatetime;

import java.time.OffsetDateTime;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractSimplePropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * This is the implementation of {@link PropertyFactory} for {@link OffsetDateTimeProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryOffsetDateTime
    extends AbstractSimplePropertyFactory<OffsetDateTime, OffsetDateTimeProperty> {

  @Override
  public Class<OffsetDateTime> getValueClass() {

    return OffsetDateTime.class;
  }

  @Override
  public Class<? extends ReadableProperty<OffsetDateTime>> getReadableInterface() {

    return ReadableOffsetDateTimeProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<OffsetDateTime>> getWritableInterface() {

    return WritableOffsetDateTimeProperty.class;
  }

  @Override
  public Class<OffsetDateTimeProperty> getImplementationClass() {

    return OffsetDateTimeProperty.class;
  }

  @Override
  public OffsetDateTimeProperty create(String name, PropertyMetadata<OffsetDateTime> metadata) {

    return new OffsetDateTimeProperty(name, metadata);
  }

}
