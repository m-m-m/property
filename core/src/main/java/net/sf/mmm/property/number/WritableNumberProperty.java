/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.number;

import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.value.observable.number.WritableNumberValue;

/**
 * {@link WritableProperty} with {@link Number} {@link #getValue() value}.
 *
 * @param <N> type of the numeric {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface WritableNumberProperty<N extends Number & Comparable<? super N>>
    extends ReadableNumberProperty<N>, WritableProperty<N>, WritableNumberValue<N> {

}
