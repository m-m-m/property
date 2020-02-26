/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.range;

import io.github.mmm.base.range.Range;
import io.github.mmm.property.ReadableProperty;

/**
 * {@link ReadableProperty} with {@link Range} {@link #get() value}.
 *
 * @param <V> type of the {@link Range} value.
 * @since 1.0.0
 */
public interface ReadableRangeProperty<V> extends ReadableProperty<Range<V>> {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  default Class<Range<V>> getValueClass() {

    return (Class) Range.class;
  }

  @Override
  default Range<V> getSafe() {

    final Range<V> value = get();
    return value == null ? Range.unbounded() : value;
  }

}
