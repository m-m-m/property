/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.month;

import java.time.Month;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractSimplePropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * This is the implementation of {@link PropertyFactory} for {@link MonthProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryMonth extends AbstractSimplePropertyFactory<Month, MonthProperty> {

  @Override
  public Class<Month> getValueClass() {

    return Month.class;
  }

  @Override
  public Class<? extends ReadableProperty<Month>> getReadableInterface() {

    return ReadableMonthProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Month>> getWritableInterface() {

    return WritableMonthProperty.class;
  }

  @Override
  public Class<MonthProperty> getImplementationClass() {

    return MonthProperty.class;
  }

  @Override
  public MonthProperty create(String name, PropertyMetadata<Month> metadata) {

    return new MonthProperty(name, metadata);
  }

}
