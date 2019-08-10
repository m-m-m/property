/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers;

import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.value.observable.numbers.WritableNumberValue;

/**
 * This is the interface for a {@link WritableProperty} of the {@link #getValue() value}-{@link #getValueClass() type}
 * {@link Number}.
 *
 * @param <N> is the generic type of the internal numeric {@link #getValue() value} representation.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface WritableNumberProperty<N extends Number & Comparable<? super N>>
    extends ReadableNumberProperty<N>, WritableProperty<N>, WritableNumberValue<N> {

}
