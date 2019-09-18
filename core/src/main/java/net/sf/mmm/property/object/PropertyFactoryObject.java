/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.object;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.property.factory.AbstractPropertyFactory;
import net.sf.mmm.property.factory.PropertyFactory;

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
  public Class<? extends V> getValueClass() {

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
  public ObjectProperty<V> create(String name, Class<? extends V> valueClass, PropertyMetadata<V> metadata) {

    return new ObjectProperty(name, valueClass, metadata);
  }

  @Override
  public boolean isPolymorphic() {

    return true;
  }

}
