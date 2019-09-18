/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.number.longs;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.property.factory.AbstractPropertyFactory;
import net.sf.mmm.property.factory.PropertyFactory;

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
  public LongProperty create(String name, Class<? extends Long> valueClass, PropertyMetadata<Long> metadata) {

    return new LongProperty(name, metadata);
  }

}
