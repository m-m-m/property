/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.list;

import java.util.List;

import io.github.mmm.property.container.collection.WritableCollectionProperty;
import io.github.mmm.value.observable.container.list.ChangeAwareList;
import io.github.mmm.value.observable.container.list.WritableListValue;

/**
 * {@link WritableCollectionProperty} with {@link List} {@link #getValue() value}.
 *
 * @param <E> type of the {@link List#contains(Object) contained elements}.
 *
 * @since 1.0.0
 */
public interface WritableListProperty<E>
    extends ReadableListProperty<E>, WritableCollectionProperty<List<E>, E>, WritableListValue<E> {

  @Override
  ChangeAwareList<E> getChangeAwareValue();

}
