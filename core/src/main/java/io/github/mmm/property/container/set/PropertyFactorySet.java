/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.set;

import java.util.Set;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractPropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;
import io.github.mmm.property.factory.PropertyTypeInfo;

/**
 * Implementation of {@link PropertyFactory} for {@link SetProperty}.
 *
 * @param <E> type of the {@link Set#contains(Object) contained elements}.
 *
 * @since 1.0.0
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PropertyFactorySet<E> extends AbstractPropertyFactory<Set<E>, SetProperty<E>> {

  @Override
  public Class<Set<E>> getValueClass() {

    return (Class) Set.class;
  }

  @Override
  public Class<? extends ReadableProperty<Set<E>>> getReadableInterface() {

    return (Class) ReadableSetProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Set<E>>> getWritableInterface() {

    return (Class) WritableSetProperty.class;
  }

  @Override
  public Class<SetProperty<E>> getImplementationClass() {

    return (Class) SetProperty.class;
  }

  @Override
  public SetProperty<E> create(String name, PropertyTypeInfo<Set<E>> typeInfo, PropertyMetadata<Set<E>> metadata) {

    return new SetProperty(name, typeInfo.getValueProperty(), metadata);
  }

  @Override
  public boolean isPolymorphic() {

    return true;
  }

}
