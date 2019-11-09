/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.list;

import java.lang.reflect.Type;
import java.util.List;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractPropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * This is the implementation of {@link PropertyFactory} for {@link ListProperty}.
 *
 * @param <E> type of the {@link List#contains(Object) contained elements}.
 *
 * @since 1.0.0
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PropertyFactoryList<E> extends AbstractPropertyFactory<List<E>, ListProperty<E>> {

  @Override
  public Class<? extends List<E>> getValueClass() {

    return (Class) List.class;
  }

  @Override
  public Class<? extends ReadableProperty<List<E>>> getReadableInterface() {

    return (Class) ReadableListProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<List<E>>> getWritableInterface() {

    return (Class) WritableListProperty.class;
  }

  @Override
  public Class<ListProperty<E>> getImplementationClass() {

    return (Class) ListProperty.class;
  }

  @Override
  public ListProperty<E> create(String name, Class<? extends List<E>> valueClass, PropertyMetadata<List<E>> metadata) {

    Class<?> componentClass = Object.class;
    Type componentType = null;
    // if (metadata.getValueType != null) {
    //
    // }
    if (componentType == null) {
      componentType = componentClass;
    }
    return new ListProperty(name, componentClass, componentType, metadata);
  }

}
