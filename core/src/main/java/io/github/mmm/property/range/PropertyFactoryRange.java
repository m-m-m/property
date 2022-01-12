/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.range;

import io.github.mmm.base.range.Range;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractPropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link RangeProperty}.
 *
 * @param <V> type of the {@link Range} values.
 * @since 1.0.0
 */
public class PropertyFactoryRange<V extends Comparable<?>> extends AbstractPropertyFactory<Range<V>, RangeProperty<V>> {

  @Override
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Class<? extends Range<V>> getValueClass() {

    return (Class) RangeProperty.class;
  }

  @Override
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Class<? extends ReadableProperty<Range<V>>> getReadableInterface() {

    return (Class) ReadableRangeProperty.class;
  }

  @Override
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Class<? extends WritableProperty<Range<V>>> getWritableInterface() {

    return (Class) WritableRangeProperty.class;
  }

  @Override
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Class<RangeProperty<V>> getImplementationClass() {

    return (Class) RangeProperty.class;
  }

  @Override
  public RangeProperty<V> create(String name, Class<? extends Range<V>> valueClass,
      PropertyMetadata<Range<V>> metadata) {

    return new RangeProperty<>(name, null, metadata);
  }

}
