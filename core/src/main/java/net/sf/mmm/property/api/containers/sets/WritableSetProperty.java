/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.containers.sets;

import java.util.List;
import java.util.Set;

import net.sf.mmm.property.api.containers.collections.WritableCollectionProperty;
import net.sf.mmm.value.observable.containers.sets.WritableSetValue;

/**
 * {@link WritableCollectionProperty} with {@link List} {@link #getValue() value}.
 *
 * @param <E> type of the {@link Set#contains(Object) contained elements}.
 *
 * @since 1.0.0
 */
public interface WritableSetProperty<E>
    extends WritableSetValue<E>, ReadableSetProperty<E>, WritableCollectionProperty<Set<E>, E> {

}
