/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.set;

import java.util.List;
import java.util.Set;

import io.github.mmm.property.container.collection.WritableCollectionProperty;
import io.github.mmm.value.observable.container.set.ChangeAwareSet;
import io.github.mmm.value.observable.container.set.WritableSetValue;

/**
 * {@link WritableCollectionProperty} with {@link List} {@link #getValue() value}.
 *
 * @param <E> type of the {@link Set#contains(Object) contained elements}.
 *
 * @since 1.0.0
 */
public interface WritableSetProperty<E>
    extends WritableSetValue<E>, ReadableSetProperty<E>, WritableCollectionProperty<Set<E>, E> {

  @Override
  ChangeAwareSet<E> getChangeAwareValue();

}
