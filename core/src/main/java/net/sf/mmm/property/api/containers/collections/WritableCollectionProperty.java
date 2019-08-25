/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.containers.collections;

import java.util.Collection;

import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.value.observable.containers.collections.WritableCollectionValue;

/**
 * {@link WritableCollectionValue} with {@link Collection} {@link #getValue() value}.
 *
 * @param <E> type of the {@link Collection#contains(Object) elements contained in the collection}.
 * @param <V> type of the {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface WritableCollectionProperty<V extends Collection<E>, E>
    extends ReadableCollectionProperty<V, E>, WritableProperty<V>, WritableCollectionValue<V, E> {

}
