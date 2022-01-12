/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.comparable;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.object.WritableSimpleProperty;

/**
 * {@link WritableProperty} with {@link Comparable} {@link #get() value}.
 *
 * @param <V> type of the {@link #get() value}.
 * @since 1.0.0
 */
public interface WritableComparableProperty<V extends Comparable<? super V>>
    extends ReadableComparableProperty<V>, WritableSimpleProperty<V> {

}
