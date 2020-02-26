/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.range;

import io.github.mmm.base.range.Range;
import io.github.mmm.property.WritableProperty;

/**
 * {@link WritableProperty} with {@link Range} {@link #get() value}.
 *
 * @param <V> type of the {@link Range} bounds.
 * @since 1.0.0
 */
public interface WritableRangeProperty<V> extends ReadableRangeProperty<V>, WritableProperty<Range<V>> {

}
