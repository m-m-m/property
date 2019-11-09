/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.collection;

import java.util.Collection;

import io.github.mmm.property.container.ReadableContainerProperty;
import io.github.mmm.value.observable.container.collection.ReadableCollectionValue;

/**
 * {@link ReadableContainerProperty} with {@link Collection} {@link #getValue() value}.
 *
 * @param <E> type of the {@link Collection#contains(Object) elements contained in the collection}.
 * @param <V> type of the {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface ReadableCollectionProperty<V extends Collection<E>, E>
    extends ReadableContainerProperty<V, E>, ReadableCollectionValue<V, E> {

}
