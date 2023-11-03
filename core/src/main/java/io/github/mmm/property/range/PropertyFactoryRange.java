/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.range;

import io.github.mmm.base.range.Range;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractPropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;
import io.github.mmm.property.factory.PropertyTypeInfo;
import io.github.mmm.property.object.SimpleProperty;

/**
 * Implementation of {@link PropertyFactory} for {@link RangeProperty}.
 *
 * @param <V> type of the {@link Range} values.
 * @since 1.0.0
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PropertyFactoryRange<V extends Comparable<?>> extends AbstractPropertyFactory<Range<V>, RangeProperty<V>> {

  @Override
  public Class<Range<V>> getValueClass() {

    return (Class) RangeProperty.class;
  }

  @Override
  public Class<? extends ReadableProperty<Range<V>>> getReadableInterface() {

    return (Class) ReadableRangeProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Range<V>>> getWritableInterface() {

    return (Class) WritableRangeProperty.class;
  }

  @Override
  public Class<RangeProperty<V>> getImplementationClass() {

    return (Class) RangeProperty.class;
  }

  @Override
  public RangeProperty<V> create(String name, PropertyTypeInfo<Range<V>> typeInfo,
      PropertyMetadata<Range<V>> metadata) {

    try {
      return new RangeProperty<>(name, (SimpleProperty) typeInfo.getValueProperty(), metadata);
    } catch (ClassCastException e) {
      throw new IllegalStateException("Range and RangeProperty only support simple values as generic type!", e);
    }
  }

}
