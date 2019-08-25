/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.containers;

import net.sf.mmm.value.observable.containers.WritableChangeAwareContainerValue;

/**
 * {@link WritableContainerProperty} with {@link net.sf.mmm.value.observable.containers.ChangeAwareContainer}
 * {@link #getValue() value}.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @param <E> type of {@link #getComponentClass() elements} contained in the {@link #getValue() value}.
 *
 * @since 1.0.0
 * @see ReadableChangeAwareContainerProperty
 * @see WritableContainerProperty
 */
public interface WritableChangeAwareContainerProperty<V, E> extends ReadableChangeAwareContainerProperty<V, E>,
    WritableContainerProperty<V, E>, WritableChangeAwareContainerValue<V, E> {

}
