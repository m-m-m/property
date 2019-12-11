/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.object.WritableSimpleProperty;
import io.github.mmm.value.observable.number.WritableNumberValue;

/**
 * {@link WritableProperty} with {@link Number} {@link #get() value}.
 *
 * @param <N> type of the numeric {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface WritableNumberProperty<N extends Number & Comparable<? super N>>
    extends ReadableNumberProperty<N>, WritableSimpleProperty<N>, WritableNumberValue<N> {

}
