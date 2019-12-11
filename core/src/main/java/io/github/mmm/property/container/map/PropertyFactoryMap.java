/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.map;

import java.util.Map;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.container.set.SetProperty;
import io.github.mmm.property.factory.AbstractPropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * This is the implementation of {@link PropertyFactory} for {@link SetProperty}.
 *
 * @param <K> the generic type of the {@link java.util.Map.Entry#getKey() keys}.
 * @param <V> the generic type of the {@link java.util.Map.Entry#getValue() values}.
 *
 * @since 1.0.0
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PropertyFactoryMap<K, V> extends AbstractPropertyFactory<Map<K, V>, MapProperty<K, V>> {

  @Override
  public Class<? extends Map<K, V>> getValueClass() {

    return (Class) Map.class;
  }

  @Override
  public Class<? extends ReadableProperty<Map<K, V>>> getReadableInterface() {

    return (Class) ReadableMapProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Map<K, V>>> getWritableInterface() {

    return (Class) WritableMapProperty.class;
  }

  @Override
  public Class<MapProperty<K, V>> getImplementationClass() {

    return (Class) MapProperty.class;
  }

  @Override
  public MapProperty<K, V> create(String name, Class<? extends Map<K, V>> valueClass,
      PropertyMetadata<Map<K, V>> metadata) {

    return new MapProperty<>(name, null, null, metadata);
  }

}
