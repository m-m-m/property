/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.longs;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractPropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link LongProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryLong extends AbstractPropertyFactory<Long, LongProperty> {

  @Override
  public Class<Long> getValueClass() {

    return Long.class;
  }

  @Override
  public Class<? extends ReadableProperty<Long>> getReadableInterface() {

    return ReadableLongProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Long>> getWritableInterface() {

    return WritableLongProperty.class;
  }

  @Override
  public Class<LongProperty> getImplementationClass() {

    return LongProperty.class;
  }

  @Override
  public LongProperty create(String name, Class<? extends Long> valueClass, PropertyMetadata<Long> metadata,
      WritableProperty<?> valueProperty) {

    return new LongProperty(name, metadata);
  }

}
