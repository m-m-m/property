/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.list;

import java.util.List;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.container.collection.ReadableCollectionProperty;
import io.github.mmm.value.observable.container.list.ReadableListValue;

/**
 * {@link ReadableProperty} with {@link List} {@link #getValue() value}.
 *
 * @param <E> type of the {@link List#contains(Object) contained elements}.
 *
 * @since 1.0.0
 */
public interface ReadableListProperty<E> extends ReadableListValue<E>, ReadableCollectionProperty<List<E>, E> {

}
