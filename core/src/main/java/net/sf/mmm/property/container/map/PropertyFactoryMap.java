/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.container.map;

import java.lang.reflect.Type;
import java.util.Map;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.property.container.set.SetProperty;
import net.sf.mmm.property.factory.AbstractPropertyFactory;
import net.sf.mmm.property.factory.PropertyFactory;

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

    Class<V> componentClass = null;
    Type componentType = null;
    return new MapProperty<>(name, componentClass, componentType, metadata);
  }

}
