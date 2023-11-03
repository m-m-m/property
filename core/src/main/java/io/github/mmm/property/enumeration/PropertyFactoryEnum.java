/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.enumeration;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractPropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;
import io.github.mmm.property.factory.PropertyTypeInfo;
import io.github.mmm.property.locale.LocaleProperty;

/**
 * Implementation of {@link PropertyFactory} for {@link LocaleProperty}.
 *
 * @param <E> type of {@link Enum} {@link EnumProperty#getValue() value}.
 * @since 1.0.0
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PropertyFactoryEnum<E extends Enum<E>> extends AbstractPropertyFactory<E, EnumProperty<E>> {

  @Override
  public Class<E> getValueClass() {

    return (Class) Enum.class;
  }

  @Override
  public Class<? extends ReadableProperty<E>> getReadableInterface() {

    return (Class) ReadableEnumProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<E>> getWritableInterface() {

    return (Class) WritableEnumProperty.class;
  }

  @Override
  public Class<EnumProperty<E>> getImplementationClass() {

    return (Class) EnumProperty.class;
  }

  @Override
  public EnumProperty<E> create(String name, PropertyTypeInfo<E> typeInfo, PropertyMetadata<E> metadata) {

    return new EnumProperty(name, metadata, typeInfo.getValueClass());
  }

}
