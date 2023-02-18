/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.shorts;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractPropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link ShortProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryShort extends AbstractPropertyFactory<Short, ShortProperty> {

  @Override
  public Class<Short> getValueClass() {

    return Short.class;
  }

  @Override
  public Class<? extends ReadableProperty<Short>> getReadableInterface() {

    return ReadableShortProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Short>> getWritableInterface() {

    return WritableShortProperty.class;
  }

  @Override
  public Class<ShortProperty> getImplementationClass() {

    return ShortProperty.class;
  }

  @Override
  public ShortProperty create(String name, Class<? extends Short> valueClass, PropertyMetadata<Short> metadata,
      WritableProperty<?> valueProperty) {

    return new ShortProperty(name, metadata);
  }

}
