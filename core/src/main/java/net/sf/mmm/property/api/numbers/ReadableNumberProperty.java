/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.value.observable.numbers.NumberExpression;

/**
 * This is the interface for a {@link ReadableProperty} of the {@link #getValue() value}-{@link #getValueClass() type}
 * {@link Number}.
 *
 * @see WritableNumberProperty
 *
 * @param <N> is the generic type of the internal numeric {@link #getValue() value} representation.
 *
 * @since 1.0.0
 */
public interface ReadableNumberProperty<N extends Number & Comparable<? super N>>
    extends ReadableProperty<N>, NumberExpression<N> {

}
