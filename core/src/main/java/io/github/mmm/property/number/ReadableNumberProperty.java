/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.value.observable.number.NumberExpression;

/**
 * {@link ReadableProperty} with {@link Number} {@link #getValue() value}.
 *
 * @see WritableNumberProperty
 *
 * @param <N> type of the internal numeric {@link #getValue() value} representation.
 *
 * @since 1.0.0
 */
public interface ReadableNumberProperty<N extends Number & Comparable<? super N>>
    extends ReadableProperty<N>, NumberExpression<N> {

}
