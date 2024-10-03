/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.dayofweek;

import java.time.DayOfWeek;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractSimplePropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * This is the implementation of {@link PropertyFactory} for {@link DayOfWeekProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryDayOfWeek extends AbstractSimplePropertyFactory<DayOfWeek, DayOfWeekProperty> {

  @Override
  public Class<DayOfWeek> getValueClass() {

    return DayOfWeek.class;
  }

  @Override
  public Class<? extends ReadableProperty<DayOfWeek>> getReadableInterface() {

    return ReadableDayOfWeekProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<DayOfWeek>> getWritableInterface() {

    return WritableDayOfWeekProperty.class;
  }

  @Override
  public Class<DayOfWeekProperty> getImplementationClass() {

    return DayOfWeekProperty.class;
  }

  @Override
  public DayOfWeekProperty create(String name, PropertyMetadata<DayOfWeek> metadata) {

    return new DayOfWeekProperty(name, metadata);
  }

}
