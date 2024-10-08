/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.duration;

import java.time.Duration;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractSimplePropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * This is the implementation of {@link PropertyFactory} for {@link DurationProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryDuration extends AbstractSimplePropertyFactory<Duration, DurationProperty> {

  @Override
  public Class<Duration> getValueClass() {

    return Duration.class;
  }

  @Override
  public Class<? extends ReadableProperty<Duration>> getReadableInterface() {

    return ReadableDurationProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Duration>> getWritableInterface() {

    return WritableDurationProperty.class;
  }

  @Override
  public Class<DurationProperty> getImplementationClass() {

    return DurationProperty.class;
  }

  @Override
  public DurationProperty create(String name, PropertyMetadata<Duration> metadata) {

    return new DurationProperty(name, metadata);
  }

}
