/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.temporal.offsettime;

import java.time.OffsetTime;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractSimplePropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * This is the implementation of {@link PropertyFactory} for {@link OffsetTimeProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryOffsetTime extends AbstractSimplePropertyFactory<OffsetTime, OffsetTimeProperty> {

  @Override
  public Class<OffsetTime> getValueClass() {

    return OffsetTime.class;
  }

  @Override
  public Class<? extends ReadableProperty<OffsetTime>> getReadableInterface() {

    return ReadableOffsetTimeProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<OffsetTime>> getWritableInterface() {

    return WritableOffsetTimeProperty.class;
  }

  @Override
  public Class<OffsetTimeProperty> getImplementationClass() {

    return OffsetTimeProperty.class;
  }

  @Override
  public OffsetTimeProperty create(String name, PropertyMetadata<OffsetTime> metadata) {

    return new OffsetTimeProperty(name, metadata);
  }

}
