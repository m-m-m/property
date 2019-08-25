/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.containers;

import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.value.observable.containers.WritableContainerValue;

/**
 * {@link WritableProperty} with container {@link #getValue() value}.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @param <E> type of {@link #getComponentClass() elements} contained in the {@link #getValue() value}.
 *
 * @since 1.0.0
 * @see ReadableContainerProperty
 */
public interface WritableContainerProperty<V, E>
    extends ReadableContainerProperty<V, E>, WritableProperty<V>, WritableContainerValue<V, E> {

}
