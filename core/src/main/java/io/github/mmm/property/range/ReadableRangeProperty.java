/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.range;

import io.github.mmm.base.range.Range;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.object.ReadableSimpleProperty;

/**
 * {@link ReadableProperty} with {@link Range} {@link #get() value}.
 *
 * @param <V> type of the {@link Range} bounds.
 * @since 1.0.0
 */
public interface ReadableRangeProperty<V extends Comparable<?>> extends ReadableSimpleProperty<Range<V>> {

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  default Class<Range<V>> getValueClass() {

    return (Class) Range.class;
  }

  @Override
  default Range<V> getFallbackSafeValue() {

    return Range.unbounded();
  }

}
