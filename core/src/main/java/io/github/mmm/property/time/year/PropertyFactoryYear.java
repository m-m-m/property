/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.year;

import java.time.Year;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractSimplePropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * This is the implementation of {@link PropertyFactory} for {@link YearProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryYear extends AbstractSimplePropertyFactory<Year, YearProperty> {

  @Override
  public Class<Year> getValueClass() {

    return Year.class;
  }

  @Override
  public Class<? extends ReadableProperty<Year>> getReadableInterface() {

    return ReadableYearProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Year>> getWritableInterface() {

    return WritableYearProperty.class;
  }

  @Override
  public Class<YearProperty> getImplementationClass() {

    return YearProperty.class;
  }

  @Override
  public YearProperty create(String name, PropertyMetadata<Year> metadata) {

    return new YearProperty(name, metadata);
  }

}
