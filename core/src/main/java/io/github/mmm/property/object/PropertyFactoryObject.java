/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.object;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractPropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;
import io.github.mmm.property.factory.PropertyTypeInfo;

/**
 * This is the implementation of {@link PropertyFactory} for {@link ObjectProperty}.
 *
 * @param <V> type of the {@link ObjectProperty#getValue() value}.
 *
 * @since 1.0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PropertyFactoryObject<V> extends AbstractPropertyFactory<V, ObjectProperty<V>> {

  @Override
  public Class<V> getValueClass() {

    return (Class) Object.class;
  }

  @Override
  public Class<? extends ReadableProperty<V>> getReadableInterface() {

    return (Class) ReadableProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<V>> getWritableInterface() {

    return (Class) WritableProperty.class;
  }

  @Override
  public Class<ObjectProperty<V>> getImplementationClass() {

    return (Class) ObjectProperty.class;
  }

  @Override
  public ObjectProperty<V> create(String name, PropertyTypeInfo<V> typeInfo, PropertyMetadata<V> metadata) {

    return new ObjectProperty(name, typeInfo.getValueClass(), metadata);
  }

  @Override
  public boolean isPolymorphic() {

    return true;
  }

}
